package com.unknown.supportapp.server.services;

import com.unknown.supportapp.common.dto.manager.ManagerDto;

public interface ManagerService {

    boolean login (ManagerDto managerDto);

    int loadIdByEmail(String email);
}
