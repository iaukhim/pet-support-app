package com.unknown.supportapp.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
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
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
