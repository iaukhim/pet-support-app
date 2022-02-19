package com.unknown.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.io.BufferedWriter;
import java.io.IOException;

public class DeleteUserProductController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode productNode = requestBody.get("productDto");
        OwnedProductDto productDto;
        try {
            productDto = objectMapper.readValue(productNode.toString(), OwnedProductDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ServiceFactory.getFactory().getOwnedProductService().deleteUserProduct(productDto);

        Response okResponse = Response.getOkResponse();
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        ObjectNode header = response.putObject("response-header");
        header.put("response-code", okResponse.getCode());
        ArrayNode body = response.putArray("response-body");
        ObjectNode bodyObject = JsonNodeFactory.instance.objectNode();
        bodyObject.put("response-message", okResponse.getMessage());
        body.add(bodyObject);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
