package com.unknown.supportapp.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.OwnedProductService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class CheckSerialController implements Controller {

    private OwnedProductService service;

    public CheckSerialController() {
    }

    public CheckSerialController(OwnedProductService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String serialNumber = requestBody.get("serialNumber").asText();

        boolean result = service.checkSerial(serialNumber);
        JsonNode response;

        if(result){
            response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());
        }
        else {
            response = ResponseFactory.getFactory().formResponse(Response.getBadRequestResponse());
        }

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
