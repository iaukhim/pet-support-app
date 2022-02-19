package com.unknown.server.entities.converters;

import com.unknown.server.entities.OwnedProduct;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.util.ArrayList;
import java.util.List;

public class OwnedProductConverter {

    public OwnedProductConverter() {
    }

    public OwnedProduct convertToEntity(OwnedProductDto dto){
        OwnedProduct ownedProduct = new OwnedProduct();

        ownedProduct.setId(dto.getId());
        ownedProduct.setOwnerId(dto.getOwnerId());

        if(dto.getType()!= null){
            ownedProduct.setType(dto.getType());
        }
        if(dto.getModel()!= null){
            ownedProduct.setModel(dto.getModel());
        }
        if(dto.getSerialNumber()!= null){
            ownedProduct.setSerialNumber(dto.getSerialNumber());
        }
        return ownedProduct;
    }

    public OwnedProductDto convertToDto(OwnedProduct entity){
        OwnedProductDto dto = new OwnedProductDto();

        dto.setId(entity.getId());
        dto.setOwnerId(entity.getOwnerId());

        if(entity.getType()!= null){
            dto.setType(entity.getType());
        }
        if(entity.getModel()!= null){
            dto.setModel(entity.getModel());
        }
        if(entity.getSerialNumber()!= null){
            dto.setSerialNumber(entity.getSerialNumber());
        }
        return dto;
    }

    public List<OwnedProductDto> convertToDtoList(List<OwnedProduct> entityList){
        ArrayList<OwnedProductDto> dtoList = new ArrayList<>();

        for (OwnedProduct entity: entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }
}
