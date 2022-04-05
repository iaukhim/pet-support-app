package com.unknown.supportapp.client.common.exception;

import com.fasterxml.jackson.databind.JsonNode;

public class ExceptionHandler {

    private CustomServerError error;

    public void handleResponse(JsonNode responseBody) throws CustomServerError {
        String title = responseBody.get("error-title").asText();
        String description = responseBody.get("error-description").asText();

        error = new CustomServerError();
        error.setErrorTitle(title);
        error.setErrorDescription(description);
        throw error;
    }
}
