package com.unknown.server.controllers.impl.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.server.common.ResponseFactory;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.server.common.Response;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class UpdateAccountController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        AccountDto account;

        JsonNode accountNode = requestBody.get("account");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            account = objectMapper.readValue(accountNode.toString(), AccountDto.class);
            ServiceFactory.getFactory().getAccountService().update(account);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
