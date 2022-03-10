package com.unknown.supportapp.server.controllers.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.OwnedProductService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadUsersProductsController implements Controller {

    private OwnedProductService service;

    public LoadUsersProductsController() {
    }

    public LoadUsersProductsController(OwnedProductService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();

        List<OwnedProductDto> productDtos = service.loadUsersProducts(email);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "owned-products", productDtos.toArray());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
