package com.ibs.service;

import com.ibs.dto.request.AccountCheckRequest;
import com.ibs.dto.response.AccountCheckResponse;

public interface AccountCheckService {
    AccountCheckResponse checkAccount(AccountCheckRequest request);
}