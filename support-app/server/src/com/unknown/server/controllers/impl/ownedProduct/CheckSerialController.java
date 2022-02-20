package com.unknown.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class CheckSerialController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String serialNumber = requestBody.get("serialNumber").asText();

        boolean result = ServiceFactory.getFactory().getOwnedProductService().checkSerial(serialNumber);
        JsonNode response;

        if(result){
            response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());
        }
        else {
            response = ResponseFactory.getFactory().formResponse(Response.getBadRequestResponse());
        }

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
