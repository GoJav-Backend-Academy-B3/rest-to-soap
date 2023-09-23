package com.phincon.wls.service;


import com.phincon.wls.model.dto.response.jaxb.AccountResponse;

import javax.xml.bind.JAXBException;

public interface AccountService {
    AccountResponse getAccount(String accNumber, String accType) throws JAXBException;
    com.phincon.wls.model.dto.response.ntv.AccountResponse
    getAccountNative(com.phincon.wls.model.dto.request.AccountResponse request)
            throws Exception;
}
