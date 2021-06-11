package com.wust.spring.boot.multi.tenant.bean.service;

import com.wust.spring.boot.multi.tenant.bean.model.request.AccountRequest;
import com.wust.spring.boot.multi.tenant.bean.model.response.AccountResponse;

public interface AccountService {
    void createRootAccount(long rootId);

    AccountResponse createAccount(AccountRequest AccountRequest);
}
