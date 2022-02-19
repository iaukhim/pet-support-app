package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.SaveUserProductService;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

public class SaveProductImpl implements SaveUserProductService {
    @Override
    public void save(OwnedProductDto productDto) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("save-product", "productDto", productDto);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server error");
        }
    }
}
