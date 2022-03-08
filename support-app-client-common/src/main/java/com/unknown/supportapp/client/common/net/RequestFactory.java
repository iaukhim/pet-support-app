package com.unknown.supportapp.client.common.net;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestFactory {

    private static final RequestFactory factory = new RequestFactory();

    private RequestFactory() {
    }

    public JsonNode formRequest(String requestType, String bodyPropertyName, Object pojo) {
        ObjectNode request = JsonNodeFactory.instance.objectNode();
        ObjectNode header = request.putObject("request-header");
        header.put("request-type", requestType);
        ObjectNode body = request.putObject("request-body");
        body.putPOJO(bodyPropertyName, pojo);
        return request;
    }

    public JsonNode formRequest(String requestType, ObjectNode requestBody) {
        ObjectNode request = JsonNodeFactory.instance.objectNode();
        ObjectNode header = request.putObject("request-header");
        header.put("request-type", requestType);
        request.putPOJO("request-body", requestBody);
        return request;
    }

    public JsonNode formRequest(String requestType) {
        ObjectNode request = JsonNodeFactory.instance.objectNode();
        ObjectNode header = request.putObject("request-header");
        header.put("request-type", requestType);
        request.putObject("request-body");
        return request;
    }

    public static RequestFactory getFactory() {
        return factory;
    }
}
