package com.unknown.server.controllers.impl.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadAllProductTypesController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        List<String> productTypes = ServiceFactory.getFactory().getProductService().loadAllProductTypes();
        Object[] array = productTypes.toArray();
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "product-types", array);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
