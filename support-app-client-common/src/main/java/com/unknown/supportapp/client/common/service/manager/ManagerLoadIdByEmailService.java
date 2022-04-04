package com.unknown.supportapp.client.common.service.manager;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface ManagerLoadIdByEmailService {
    int load(String email) throws CustomServerError;
}
