package com.phincon.wls.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.phincon.wls.model.entity.ResponseData2;
import com.phincon.wls.service.AccountHistoryService;
import com.phincon.wls.service.AccountService;
import com.phincon.wls.service.CreditCardService;
import com.phincon.wls.service.InquiryAccountService;

@RestController
@RequestMapping("/v1")
public class UseCase2 {


    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CreditCardService ccService;

    @Autowired
    private AccountHistoryService histService;
    
    @Autowired
    private InquiryAccountService inqService;
    
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    
    @GetMapping("/usecase2/{cifnbr}")
    public ResponseEntity<DataResponse<ResponseData2>> getInquiryAccount(@PathVariable String cifnbr) {

        List<Account> accounts = inqService.inquiryAccount(new CifNumber(cifnbr));
        
        List<CreditCard> result = ccService.queryCreditCard(new CifNumber(cifnbr));
        
        Calendar cal = Calendar.getInstance();        
    	cal.add(Calendar.DATE, -1);
    	String endDate = sdf.format(cal.getTime());        
    	cal.add(Calendar.MONTH, -1);
    	cal.set(Calendar.DATE, 1);
    	String startDate = sdf.format(cal.getTime());
    	String strindex = "0";  
    	List<Mutasi> listMutasi = new ArrayList<Mutasi>();
    	List<List<Mutasi>> listsMutasi = new ArrayList<>();
        for (int i=0; i<accounts.size(); i++) {
        	String accNo = accounts.get(i).getAccountNbr();
        	//System.out.println("======> cif "+ cif);
        	String accType = accounts.get(i).getProductTp();
        	
        	WsAccountHistoryRequest accountHistoryRequest = new WsAccountHistoryRequest(accNo, accType,
        		startDate, endDate, strindex);
        
        	//List<Mutasi> mutasis = histService.queryAccountHistory(accountHistoryRequest);
        	listMutasi = histService.queryAccountHistory(accountHistoryRequest);
        	
        	listsMutasi.add(listMutasi);
        }
        
        ResponseData2 rd = new ResponseData2();       
        rd.setAccount(accounts);
        rd.setAccountHistory(listsMutasi);
    	rd.setCreditCard(result);
        
        return DataResponse.ok(rd);
    }

    
}