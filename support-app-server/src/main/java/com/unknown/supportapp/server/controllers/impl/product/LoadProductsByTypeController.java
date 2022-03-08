package com.unknown.supportapp.server.controllers.impl.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.product.ProductDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadProductsByTypeController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String type = requestBody.get("type").asText();

        List<ProductDto> productDtos = ServiceFactory.getFactory().getProductService().loadProductsByType(type);
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "products", productDtos.toArray());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
