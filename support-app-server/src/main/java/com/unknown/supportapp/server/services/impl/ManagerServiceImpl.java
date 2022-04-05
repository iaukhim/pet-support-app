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
    private ManagerConverter managerConverter;

    public ManagerServiceImpl() {
    }

    public ManagerServiceImpl(ManagerDao managerDao, ManagerConverter managerConverter) {
        this.managerDao = managerDao;
        this.managerConverter = managerConverter;
    }

    @Override
    public boolean login(ManagerDto managerDto) {
        Manager manager = managerConverter.convertToEntity(managerDto);

        boolean result = managerDao.login(manager);

        return result;
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = managerDao.loadIdByEmail(email);
        return id;
    }
}
