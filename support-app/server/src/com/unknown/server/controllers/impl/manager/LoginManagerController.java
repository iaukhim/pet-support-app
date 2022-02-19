package com.unknown.server.controllers.impl.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
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

        Response okResponse = Response.getOkResponse();
        Response badRequestResponse = Response.getBadRequestResponse();
        ObjectNode response = null;

        if (result) {
            response = JsonNodeFactory.instance.objectNode();
            ObjectNode header = response.putObject("response-header");
            header.put("response-code", okResponse.getCode());
            ArrayNode body = response.putArray("response-body");
            ObjectNode bodyObject = JsonNodeFactory.instance.objectNode();
            bodyObject.put("response-message", okResponse.getMessage());
            body.add(bodyObject);
        } else {
            response = JsonNodeFactory.instance.objectNode();
            ObjectNode header = response.putObject("response-header");
            header.put("response-code", badRequestResponse.getCode());
            ArrayNode body = response.putArray("response-body");
            ObjectNode bodyObject = JsonNodeFactory.instance.objectNode();
            bodyObject.put("response-message", badRequestResponse.getMessage());
            body.add(bodyObject);
        }

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
