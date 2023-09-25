package com.phincon.wls.controller;

import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import com.phincon.wls.model.dto.response.jaxb.AccountResponse;
import com.phincon.wls.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<DataResponse<AccountResponse>> getAccountDetail(@RequestBody com.phincon.wls.model.dto.request.AccountResponse userRequest) throws JAXBException {

        AccountResponse accountResponse = accountService.getAccount(userRequest.getAcctNbr(), userRequest.getAcctType());

        return DataResponse.ok(accountResponse);
    }

    @PostMapping("/account-native")
    public ResponseEntity<DataResponse<com.phincon.wls.model.dto.response.ntv.AccountResponse>> getAccountDetailNative(
            @RequestBody com.phincon.wls.model.dto.request.AccountResponse request)
            throws Exception {
        return DataResponse.ok(accountService.getAccountNative(request));
    }
}
