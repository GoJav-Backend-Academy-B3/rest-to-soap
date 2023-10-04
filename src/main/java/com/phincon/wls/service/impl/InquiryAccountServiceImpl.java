package com.phincon.wls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.inquiryaccount.WsInquiryAccountResponse;
import com.phincon.wls.model.entity.Account;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.service.InquiryAccountService;
import com.phincon.wls.utils.LogUtils;

@Service
public class InquiryAccountServiceImpl implements InquiryAccountService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${ws.inqacc.url}")
    private String wsInqaccUrl;

    LogUtils logs = new LogUtils();
    
    @Override
    public List<Account> inquiryAccount(CifNumber cif) {
        final String templateUrl = String.format("%s/INQACCT/{cifnbr}", wsInqaccUrl);
        logs.printLog(templateUrl);
        WsInquiryAccountResponse response = restTemplate.getForObject(templateUrl,
                WsInquiryAccountResponse.class, cif.getCif());
        logs.printLog(response.toString());
        return response.getDataRaw();
    }

}
