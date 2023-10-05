package com.phincon.wls.service.impl;

import com.phincon.wls.model.dto.accounthistory.WsAccountHistoryRequest;
import com.phincon.wls.model.dto.response.jaxb.AccountResponse;
import com.phincon.wls.model.entity.*;
import com.phincon.wls.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UseCaseServiceImpl implements UseCaseService {

	private static final Logger log = LoggerFactory.getLogger(UseCaseServiceImpl.class);
	
    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditCardService ccService;

    @Autowired
    private AccountHistoryService histService;

    @Autowired
    private InquiryAccountService inqService;

    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

    @Override
    public ResponseData1 useCaseData(String number, String accountType) throws Exception {
        AccountResponse accountResponse = accountService.getAccount(number, accountType);

        String cif = accountResponse.getCif();
        System.out.println("======> cif "+ cif);

        List<CreditCard> creditCards = ccService.queryCreditCardByCardNumber(cif);
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String endDate = sdf.format(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        String startDate = sdf.format(cal.getTime());
        String strindex = "0";

        WsAccountHistoryRequest accountHistoryRequest = new WsAccountHistoryRequest(number,
                accountType, startDate, endDate, strindex);

        List<Mutasi> mutasis = histService.queryAccountHistory(accountHistoryRequest);
        
        ResponseData1 responseData1 = new ResponseData1();
        responseData1.setAccountResponse(accountResponse);
        responseData1.setAccountHistory(mutasis);
        responseData1.setCreditCard(creditCards);
        return responseData1;
    }

    @Override
    public ResponseData2 useCaseData2(String cifNumber) throws Exception {
    	ResponseData2 responseData2 = new ResponseData2();
        List<Account> accounts = inqService.inquiryAccount(new CifNumber(cifNumber));
        System.out.println("result inquiry service length :: "+accounts.size());
        
        List<List<CreditCard>> listsCreditCard = new ArrayList<>();
        List<CreditCard> cifCc = ccService.queryCreditCardByCardNumber(cifNumber);
        if(cifCc.size()>0)
        	listsCreditCard.add(cifCc);
        
        for (int i=0; i<accounts.size(); i++) {
        	if("CC".equals(accounts.get(i).getProductTp().trim().toUpperCase())) {
	        	List<CreditCard> creditCards = ccService.queryCreditCardByCardNumber(accounts.get(i).getAccountNbr().trim());
	            System.out.println("credit card service length :: "+creditCards.size());
	            
	            if(creditCards.size()> 0 ) {
	            	listsCreditCard.add(creditCards);
	            }
        	}            
        }
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String endDate = sdf.format(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        String startDate = sdf.format(cal.getTime());
        String strindex = "0";
        
        List<List<Mutasi>> listsMutasi = new ArrayList<>();
        for (int i=0; i<accounts.size(); i++) {
        	List<Mutasi> listMutasi = new ArrayList<Mutasi>();
            String accNo = accounts.get(i).getAccountNbr().trim();
            //System.out.println("======> cif "+ cif);
            String accType = accounts.get(i).getProductTp().trim();

            WsAccountHistoryRequest accountHistoryRequest = new WsAccountHistoryRequest(accNo, accType,
                    startDate, endDate, strindex);

            //List<Mutasi> mutasis = histService.queryAccountHistory(accountHistoryRequest);
            listMutasi = histService.queryAccountHistory(accountHistoryRequest);
            if(listMutasi.size()> 0)
            	listsMutasi.add(listMutasi);
                        
        }              
        
        responseData2.setAccount(accounts);
        responseData2.setCreditCard(listsCreditCard);
        responseData2.setAccountHistory(listsMutasi);

        return responseData2;
    }
}
