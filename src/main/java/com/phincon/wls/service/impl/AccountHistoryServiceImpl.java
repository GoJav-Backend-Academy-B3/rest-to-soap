package com.phincon.wls.service.impl;

import java.net.SocketTimeoutException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.exception.custom.ConnectionTimeoutException;
import com.phincon.wls.exception.custom.NotFoundException;
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
        ResponseEntity<WsAccountHistoryResponse> responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(urlTemplate,
                    WsAccountHistoryResponse.class, request.getAcctNbr(), request.getAcctTp(),
                    request.getStrDate(), request.getEndDate(), request.getStrIndex());
        } catch (RestClientException e) {
            // SocketTimeoutException is the impostor
            if (e.getCause() instanceof SocketTimeoutException) {
                throw new ConnectionTimeoutException(String.format("Connection to service timed out.", wsAccthstUrl));
            }
            throw e;
        }

        if (responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException(
                    String.format("Parameter not found: (ACCTNBR=%s, ACCTTP=%s, STRDATE=%s, ENDDATE=%s, STRINDEX=%s)",
                            request.getAcctNbr(), request.getAcctTp(),
                            request.getStrDate(), request.getEndDate(), request.getStrIndex()));
        }

        List<Mutasi> mutasi = responseEntity.getBody().getMutasi();

        return mutasi;
    }
}
