package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface LoadIdByEmailService {
    int load (String email) throws CustomServerError;
}
