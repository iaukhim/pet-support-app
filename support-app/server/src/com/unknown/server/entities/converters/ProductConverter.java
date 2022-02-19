package com.unknown.server.entities.converters;

import com.unknown.server.entities.Product;
import com.unknown.supportapp.common.dto.product.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {
    public ProductConverter() {
    }

    public ProductDto convertToDto(Product entity){
        ProductDto productDto = new ProductDto();

        productDto.setId(entity.getId());

        if(entity.getType() != null){
            productDto.setType(entity.getType());
        }
        if(entity.getModel() != null){
            productDto.setModel(entity.getModel());
        }
        return productDto;
    }

    public List<ProductDto> convertToDtoList(List<Product> entityList){
        ArrayList<ProductDto> dtoList = new ArrayList<>();

        for (Product entity: entityList) {
            ProductDto dto = convertToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
