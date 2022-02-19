package com.unknown.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class ChangeSerialController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String oldValue = requestBody.get("oldValue").asText();
        String newValue = requestBody.get("newValue").asText();

        boolean result = ServiceFactory.getFactory().getOwnedProductService().changeSerial(oldValue, newValue);

        Response okResponse = Response.getOkResponse();

        ObjectNode response = JsonNodeFactory.instance.objectNode();
        ObjectNode header = response.putObject("response-header");
        header.put("response-code", okResponse.getCode());
        ObjectNode body = response.putObject("response-body");
        body.put("response-message", okResponse.getMessage());
        body.put("result", result);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
