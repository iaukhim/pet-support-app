package com.unknown.supportapp.server.controllers.impl.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.AccountService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.acccount.AccountDto;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConfirmationController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDto accountDto;

        try {
            accountDto = objectMapper.readValue(requestBody.get("account").toString(), AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        AccountService accountService = ServiceFactory.getFactory().getAccountService();
        accountService.saveAccount(accountDto);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
