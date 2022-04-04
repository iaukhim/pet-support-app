package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.ManagerDao;
import com.unknown.supportapp.server.entities.Manager;
import com.unknown.supportapp.server.entities.converters.ManagerConverter;
import com.unknown.supportapp.server.services.ManagerService;
import com.unknown.supportapp.common.dto.manager.ManagerDto;

import javax.transaction.Transactional;

@Transactional
public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao;

    public ManagerServiceImpl() {
    }

    public ManagerServiceImpl(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public boolean login(ManagerDto managerDto) {
        Manager manager = new ManagerConverter().convertToEntity(managerDto);

        boolean result = managerDao.login(manager);

        return result;
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = managerDao.loadIdByEmail(email);
        return id;
    }
}
