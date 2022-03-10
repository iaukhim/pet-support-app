package com.unknown.supportapp.server.controllers.impl.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.supportapp.server.services.AccountService;

import java.io.BufferedWriter;
import java.io.IOException;


public class ChangePasswordController implements Controller {

    private AccountService service;

    public ChangePasswordController(AccountService service) {
        this.service = service;
    }

    public ChangePasswordController() {
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {

        ObjectMapper objectMapper = new ObjectMapper();
        AccountDto accountDto = null;

        try {
            accountDto = objectMapper.readValue(requestBody.get("account").toString(), AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        service.changePassword(accountDto);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
