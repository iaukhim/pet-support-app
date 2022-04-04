package com.unknown.supportapp.client.common.service.manager;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.common.dto.manager.ManagerDto;

public interface ManagerLoginService {
    boolean login(ManagerDto managerDto) throws CustomServerError;
}
