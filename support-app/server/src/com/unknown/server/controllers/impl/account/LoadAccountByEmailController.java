package com.unknown.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoadAccountByEmailController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();
        AccountDto accountDto = ServiceFactory.getFactory().getAccountService().loadByEmail(email);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "account", accountDto);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
