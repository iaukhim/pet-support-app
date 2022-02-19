package com.unknown.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadUsersProductsController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();

        List<OwnedProductDto> productDtos = ServiceFactory.getFactory().getOwnedProductService().loadUsersProducts(email);
        Object[] array = productDtos.toArray();

        Response okResponse = Response.getOkResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response;

        response = JsonNodeFactory.instance.objectNode();
        ObjectNode header = response.putObject("response-header");
        header.put("response-code", okResponse.getCode());
        ObjectNode body = response.putObject("response-body");
        body.put("response-message", okResponse.getMessage());
        body.putPOJO("owned-products", array);

        String responseString = response.toString();

        try {
            writer.write(responseString, 0, responseString.length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
