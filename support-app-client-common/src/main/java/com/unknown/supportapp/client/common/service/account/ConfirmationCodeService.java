package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface ConfirmationCodeService {

    String send(String email) throws CustomServerError;
}
