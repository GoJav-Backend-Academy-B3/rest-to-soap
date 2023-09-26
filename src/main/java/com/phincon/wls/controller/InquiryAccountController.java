package com.phincon.wls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import com.phincon.wls.model.entity.Account;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.service.InquiryAccountService;

@RestController
@RequestMapping("/v1/inqacc")
public class InquiryAccountController {
    
    @Autowired
    private InquiryAccountService service;

    @GetMapping("/{cifnbr}")
    public ResponseEntity<DataResponse<List<Account>>> getInquiryAccount(@PathVariable String cifnbr) {

        List<Account> accounts = service.inquiryAccount(new CifNumber(cifnbr));

        return DataResponse.ok(accounts);
    }
}
