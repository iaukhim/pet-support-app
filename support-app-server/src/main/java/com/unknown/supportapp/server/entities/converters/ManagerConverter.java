package com.unknown.supportapp.server.entities.converters;

import com.unknown.supportapp.server.entities.Manager;
import com.unknown.supportapp.common.dto.manager.ManagerDto;

public class ManagerConverter {

    public Manager convertToEntity(ManagerDto dto){
        Manager entity = new Manager();
        entity.setId(dto.getId());

        if(dto.getEmail() != null){
            entity.setEmail(dto.getEmail());
        }
        if(dto.getPassword() != null){
            entity.setPassword(dto.getPassword());
        }
        return entity;
    }
}
