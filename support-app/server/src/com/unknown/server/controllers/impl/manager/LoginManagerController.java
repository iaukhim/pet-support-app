package com.unknown.server.controllers.impl.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.manager.ManagerDto;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoginManagerController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {

        boolean result = false;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ManagerDto managerDto = null;
        try {
            managerDto = objectMapper.readValue(requestBody.get("account").toString(), ManagerDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        result = ServiceFactory.getFactory().getManagerService().login(managerDto);
        JsonNode response = null;

        if (result) {
            response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());
        } else {
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
