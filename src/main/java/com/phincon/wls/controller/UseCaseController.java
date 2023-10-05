package com.phincon.wls.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.phincon.wls.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.wls.model.dto.accounthistory.WsAccountHistoryRequest;
import com.phincon.wls.model.dto.request.AccountRequest;
import com.phincon.wls.model.dto.response.jaxb.AccountResponse;
import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import com.phincon.wls.model.entity.Account;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.model.entity.Mutasi;
import com.phincon.wls.model.entity.ResponseData1;
import com.phincon.wls.model.entity.ResponseData2;


@RestController
@RequestMapping("/v1")
public class UseCaseController {

	@Autowired
    private AccountService accountService;
    
    @Autowired
    private CreditCardService ccService;

    @Autowired
    private AccountHistoryService histService;
    
    @Autowired
    private InquiryAccountService inqService;
    
    @Autowired
    private UseCaseService useCaseService;
    
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    
    @PostMapping("/usecase1")
    public ResponseEntity<DataResponse<ResponseData1>> getAccountDetail(@RequestBody AccountRequest userRequest) throws Exception {
        ResponseData1 rd = useCaseService.useCaseData(userRequest.getAcctNbr(), userRequest.getAcctType());
        
        return DataResponse.ok(rd);
    }
    
    
    @GetMapping("/usecase2/{cifnbr}")
    public ResponseEntity<DataResponse<ResponseData2>> getInquiryAccount(@PathVariable String cifnbr) throws Exception {
        ResponseData2 rd = useCaseService.useCaseData2(cifnbr);
        
        return DataResponse.ok(rd);
    }
}
