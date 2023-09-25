package com.phincon.wls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.creditcard.WsCreditCardRequest;
import com.phincon.wls.model.dto.creditcard.WsCreditCardRequestBody;
import com.phincon.wls.model.dto.creditcard.WsCreditCardRequestHeader;
import com.phincon.wls.model.dto.creditcard.WsCreditCardResponse;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${ws.creditcard.url}")
    private String wsCreditCardUrl;

    @Override
    public List<CreditCard> queryCreditCard(String number)  {
        
        // TODO Should change this to desired data because I don't know what to fill
        final WsCreditCardRequestHeader rqHeader = WsCreditCardRequestHeader.builder()
                .service("listCustomerCIFCardSummaryListInput")
                .traceId("ABCDEFHIJKLMNOPQRSTUVWXY7")
                .channel("CC")
                .timestamp("2023-09-19 15:48:57.821").build();
        final WsCreditCardRequest body = WsCreditCardRequest.builder()
                .rqHeader(rqHeader)
                .rqBody(new WsCreditCardRequestBody(number))
                .build();

        final HttpEntity<WsCreditCardRequest> request = new HttpEntity<>(body);
        ResponseEntity<WsCreditCardResponse> responseEntity = restTemplate.exchange(wsCreditCardUrl, HttpMethod.POST, request,
                WsCreditCardResponse.class);

        WsCreditCardResponse response = responseEntity.getBody();

        return response.getRsBody();
    }

}
