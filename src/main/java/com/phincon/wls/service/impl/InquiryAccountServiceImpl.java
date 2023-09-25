package com.phincon.wls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.inquiryaccount.WsInquiryAccountResponse;
import com.phincon.wls.model.entity.Account;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.service.InquiryAccountService;

public class InquiryAccountServiceImpl implements InquiryAccountService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${ws.inqacc.url}")
    private String wsInqaccUrl;

    @Override
    public List<Account> inquiryAccount(CifNumber cif) {
        final String templateUrl = String.format("%s/INQACCT/{cifnbr}", wsInqaccUrl);
        WsInquiryAccountResponse response = restTemplate.getForObject(templateUrl,
                WsInquiryAccountResponse.class, cif.getCif());

        return response.getDataRaw();
    }

}
