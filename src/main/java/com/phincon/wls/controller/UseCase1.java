package com.phincon.wls.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.phincon.wls.service.AccountHistoryService;
import com.phincon.wls.service.AccountService;
import com.phincon.wls.service.CreditCardService;

@RestController
@RequestMapping("/v1")
public class UseCase1 {


    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CreditCardService ccService;

    @Autowired
    private AccountHistoryService histService;
    
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    
    @PostMapping("/usecase1")
    public ResponseEntity<DataResponse<ResponseData1>> getAccountDetail(@RequestBody AccountRequest userRequest) throws Exception {
        AccountResponse accountResponse = accountService.getAccount(userRequest.getAcctNbr(), userRequest.getAcctType());

        String cif = accountResponse.getCif();
        System.out.println("======> cif "+ cif);
        
        Calendar cal = Calendar.getInstance();        
        cal.add(Calendar.DATE, -1);
        String endDate = sdf.format(cal.getTime());        
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        String startDate = sdf.format(cal.getTime());
        String strindex = "0";        
        
        WsAccountHistoryRequest accountHistoryRequest = new WsAccountHistoryRequest(userRequest.getAcctNbr(),
        		userRequest.getAcctType(), startDate, endDate, strindex);
        
        List<Mutasi> mutasis = histService.queryAccountHistory(accountHistoryRequest);
        
        //ResponseEntity<DataResponse<List<CreditCard>>> queryCreditCardByCif(@PathVariable String cif) {

        List<CreditCard> result = ccService.queryCreditCard(new CifNumber(cif));

        ResponseData1 rd = new ResponseData1();       
        rd.setAccountResponse(accountResponse);
        rd.setAccountHistory(mutasis);
    	rd.setCreditCard(result);
    	
        
        return DataResponse.ok(rd);
    }

    
}