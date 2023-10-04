package com.phincon.wls.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.creditcard.WsCreditCardRequest;
import com.phincon.wls.model.dto.creditcard.WsCreditCardRequestBody;
import com.phincon.wls.model.dto.creditcard.WsCreditCardRequestHeader;
import com.phincon.wls.model.dto.creditcard.WsCreditCardResponse;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.service.CreditCardService;
import com.phincon.wls.utils.LogUtils;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${ws.creditcard.url}")
    private String wsCreditCardUrl;

    LogUtils logs = new LogUtils();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
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
        
        
        logs.printLog(request.toString());
        
        ResponseEntity<WsCreditCardResponse> responseEntity = restTemplate.exchange(wsCreditCardUrl, HttpMethod.POST, request,
                WsCreditCardResponse.class);

        WsCreditCardResponse response = responseEntity.getBody();

        logs.printLog(response.toString());
        
        return response.getRsBody();
    }

    
    @Override    
    public List<CreditCard> queryCreditCardByCardNumber(String number)  {
        
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
    	String reqJson = "{\"rqHeader\": {\"service\": \"listCustomerCIFCardSummary\",\"traceID\": \"ABCDEFHIJKLMNOPQRSTUVWXY7\",\"channel\": \"CC\",\"timestamp\": \"{date_time}\"}, \"rqBody\": {\"cust\": \"{card_nomor}\"}}";
        
    	reqJson = reqJson.replace("{card_nomor}", number);
    	reqJson = reqJson.replace("{date_time}", sdf.format(new Date()));
    	    	
        logs.printLog(reqJson.toString());
        HttpEntity<String> requestEntity = new HttpEntity<>(reqJson, headers);
        ResponseEntity<WsCreditCardResponse> responseEntity = restTemplate.exchange(wsCreditCardUrl, HttpMethod.POST, 
        		requestEntity, WsCreditCardResponse.class);

        WsCreditCardResponse response = responseEntity.getBody();

        logs.printLog(response.toString());
        
        return response.getRsBody();
    }

}
