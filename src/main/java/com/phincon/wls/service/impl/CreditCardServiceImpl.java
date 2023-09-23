package com.phincon.wls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.request.CreditCardRequest;
import com.phincon.wls.model.dto.request.CreditCardRequestBody;
import com.phincon.wls.model.dto.request.CreditCardRequestHeader;
import com.phincon.wls.model.dto.response.CreditCardResponse;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.service.CreditCardService;

public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${ws.creditcard.url}")
    private String wsCreditCardUrl;

    @Override
    public List<CreditCard> queryCreditCard(String number)  {
        
        // TODO Should change this to desired data because I don't know what to fill
        final CreditCardRequestHeader rqHeader = CreditCardRequestHeader.builder()
                .service("listCustomerCIFCardSummaryListInput")
                .traceId("ABCDEFHIJKLMNOPQRSTUVWXY7")
                .channel("CC")
                .timestamp("2023-09-19 15:48:57.821").build();
        final CreditCardRequest body = CreditCardRequest.builder()
                .rqHeader(rqHeader)
                .rqBody(new CreditCardRequestBody(number))
                .build();

        final HttpEntity<CreditCardRequest> request = new HttpEntity<>(body);
        ResponseEntity<CreditCardResponse> responseEntity = restTemplate.exchange(wsCreditCardUrl, HttpMethod.POST, request,
                CreditCardResponse.class);

        CreditCardResponse response = responseEntity.getBody();

        return response.getRsBody();
    }

}
