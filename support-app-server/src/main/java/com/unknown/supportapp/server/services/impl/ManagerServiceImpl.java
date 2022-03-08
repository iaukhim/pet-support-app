package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.factory.DaoFactory;
import com.unknown.supportapp.server.entities.Manager;
import com.unknown.supportapp.server.entities.converters.ManagerConverter;
import com.unknown.supportapp.server.services.ManagerService;
import com.unknown.supportapp.common.dto.manager.ManagerDto;

public class ManagerServiceImpl implements ManagerService {
    @Override
    public boolean login(ManagerDto managerDto) {
        Manager manager = new ManagerConverter().convertToEntity(managerDto);

        boolean result = DaoFactory.getFactory().getManagerDao().login(manager);

        return result;
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = DaoFactory.getFactory().getManagerDao().loadIdByEmail(email);
        return id;
    }
}
