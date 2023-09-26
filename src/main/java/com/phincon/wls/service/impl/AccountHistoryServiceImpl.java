package com.phincon.wls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.accounthistory.WsAccountHistoryRequest;
import com.phincon.wls.model.dto.accounthistory.WsAccountHistoryResponse;
import com.phincon.wls.model.entity.Mutasi;
import com.phincon.wls.service.AccountHistoryService;

@Service
public class AccountHistoryServiceImpl implements AccountHistoryService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${ws.accthst.url}")
    private String wsAccthstUrl;

    @Override
    public List<Mutasi> queryAccountHistory(WsAccountHistoryRequest request) {
        final String urlTemplate = String.format("%s/ACCTHST/{ACCTNBR}/{ACCTTP}/{STRDATE}/{ENDDATE}/{STRINDEX}",
                wsAccthstUrl);
        WsAccountHistoryResponse response = restTemplate.getForObject(urlTemplate, WsAccountHistoryResponse.class, request.getAcctNbr(), request.getAcctTp(),
                request.getStrDate(), request.getEndDate(), request.getStrIndex());
        
        List<Mutasi> mutasi = response.getMutasi();

        return mutasi;
    }
}
