package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface DeleteById {

    void delete(int id) throws CustomServerError;
}
