package com.phincon.wls.service;


import com.phincon.wls.model.dto.request.AccountRequest;
import com.phincon.wls.model.dto.response.jaxb.AccountResponse;

import javax.xml.bind.JAXBException;

public interface AccountService {
    AccountResponse getAccount(String accNumber, String accType) throws Exception;
    com.phincon.wls.model.dto.response.ntv.AccountResponse
    getAccountNative(AccountRequest request)
            throws Exception;
    
    AccountResponse getPostAccount(String accNumber, String accType) throws Exception;
}
