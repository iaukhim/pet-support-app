package com.unknown.server.controllers.impl.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.product.ProductDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadProductsByTypeController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String type = requestBody.get("type").asText();

        List<ProductDto> productDtos = ServiceFactory.getFactory().getProductService().loadProductsByType(type);
        Object[] array = productDtos.toArray();
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "products", array);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
