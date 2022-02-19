package com.unknown.server.controllers.impl.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.product.ProductDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadAllProductsController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {

        List<ProductDto> productDtos = ServiceFactory.getFactory().getProductService().loadAllProducts();
        Object[] array = productDtos.toArray();

        Response okResponse = Response.getOkResponse();
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        ObjectNode header = response.putObject("response-header");
        header.put("response-code", okResponse.getCode());
        ObjectNode body = response.putObject("response-body");
        body.putPOJO("products", array);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
