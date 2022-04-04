package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.SaveUserProductService;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

public class SaveProductImpl implements SaveUserProductService {

    private ExceptionHandler exceptionHandler;

    @Override
    public void save(OwnedProductDto productDto) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("save-product", "productDto", productDto);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();
        if (responseCode >= 500) {
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }
    }
}
