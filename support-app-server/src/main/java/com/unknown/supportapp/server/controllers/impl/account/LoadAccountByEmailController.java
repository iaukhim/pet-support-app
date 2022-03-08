package com.unknown.supportapp.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.supportapp.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoadAccountByEmailController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();
        AccountDto accountDto = ServiceFactory.getFactory().getAccountService().loadByEmail(email);
        JsonNode response;

        if(accountDto == null){
            response = ResponseFactory.getFactory().formResponse(Response.getBadRequestResponse());
        }
        else{
            response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "account", accountDto);
        }

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
