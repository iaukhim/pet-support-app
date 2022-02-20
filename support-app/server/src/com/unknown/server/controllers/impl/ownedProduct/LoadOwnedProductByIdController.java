package com.unknown.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoadOwnedProductByIdController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        int id = requestBody.get("id").asInt();

        OwnedProductDto productDto = ServiceFactory.getFactory().getOwnedProductService().loadById(id);
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "product", productDto);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
