package com.unknown.server.entities.converters;

import com.unknown.server.entities.Ticket;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.util.ArrayList;
import java.util.List;

public class TicketConverter {

    public TicketDto convertToDto(Ticket entity){
        TicketDto dto = new TicketDto();

        dto.setId(entity.getId());
        dto.setStarterId(entity.getStarterId());
        dto.setManagerId(entity.getManagerId());
        dto.setStatus(entity.getStatus());
        dto.setProductId(entity.getProductId());

        if(entity.getTheme() != null){
            dto.setTheme(entity.getTheme());
        }
        if(entity.getText() != null){
            dto.setText(entity.getText());
        }

        return dto;
    }

    public List<TicketDto> convertToDtoList(List<Ticket> entityList){
        ArrayList<TicketDto> dtoList = new ArrayList<>();

        for (Ticket entity: entityList) {
            TicketDto dto = convertToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Ticket convertToEntity(TicketDto dto){
        Ticket entity = new Ticket();

        entity.setId(dto.getId());
        entity.setStarterId(dto.getStarterId());
        entity.setManagerId(dto.getManagerId());
        entity.setStatus(dto.getStatus());
        entity.setProductId(dto.getProductId());

        if(dto.getTheme() != null){
            entity.setTheme(dto.getTheme());
        }
        if(dto.getText() != null){
            entity.setText(dto.getText());
        }

        return entity;
    }
}
