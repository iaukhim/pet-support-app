package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.DeleteUserProductSevice;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

public class DeleteUserProductImpl implements DeleteUserProductSevice {
    @Override
    public void delete(OwnedProductDto productDto) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("delete-user-product", "productDto", productDto);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if(responseCode != 200){
            throw new RuntimeException("Server error");
        }
    }
}
