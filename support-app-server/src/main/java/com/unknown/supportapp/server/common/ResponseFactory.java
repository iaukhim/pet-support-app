package com.unknown.supportapp.server.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResponseFactory {

    private static final ResponseFactory factory = new ResponseFactory();

    private ResponseFactory() {
    }

    public JsonNode formResponse(Response response, String bodyPropertyName, Object pojo) {
        ObjectNode jsonResponse = JsonNodeFactory.instance.objectNode();
        ObjectNode header = jsonResponse.putObject("response-header");
        header.put("response-code", response.getCode());
        ObjectNode body = jsonResponse.putObject("response-body");
        body.put("response-message", response.getMessage());
        body.putPOJO(bodyPropertyName, pojo);
        return jsonResponse;
    }

    public JsonNode formResponse(Response response, ObjectNode responseBody) {
        ObjectNode jsonResponse = JsonNodeFactory.instance.objectNode();
        ObjectNode header = jsonResponse.putObject("response-header");
        header.put("response-code", response.getCode());
        responseBody.put("response-message", response.getMessage());
        jsonResponse.putPOJO("response-body", responseBody);
        return jsonResponse;
    }

    public JsonNode formResponse(Response response) {
        ObjectNode jsonResponse = JsonNodeFactory.instance.objectNode();
        ObjectNode header = jsonResponse.putObject("response-header");
        header.put("response-code", response.getCode());
        ObjectNode body = jsonResponse.putObject("response-body");
        body.put("response-message", response.getMessage());
        return jsonResponse;
    }
    public JsonNode formResponse(Response response,Exception e) {
        ObjectNode jsonResponse = JsonNodeFactory.instance.objectNode();
        ObjectNode header = jsonResponse.putObject("response-header");
        header.put("response-code", response.getCode());
        ObjectNode body = jsonResponse.putObject("response-body");
        body.put("error-title", response.getMessage());
        body.put("error-description", e.getMessage());
        return jsonResponse;
    }


    public static ResponseFactory getFactory() {
        return factory;
    }
}

