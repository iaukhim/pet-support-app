package com.unknown.supportapp.server.controllers.impl.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.ProductService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.product.ProductDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadAllProductsController implements Controller {

    private ProductService service;

    public LoadAllProductsController() {
    }

    public LoadAllProductsController(ProductService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        List<ProductDto> productDtos = service.loadAllProducts();
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "products", productDtos.toArray());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
