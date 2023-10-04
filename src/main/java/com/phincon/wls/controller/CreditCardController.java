package com.phincon.wls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.service.CreditCardService;

@RestController
@RequestMapping("/v1/creditcard")
public class CreditCardController {
    
    @Autowired
    private CreditCardService service;

    @GetMapping("/cif/{cif}")
    public ResponseEntity<DataResponse<List<CreditCard>>> queryCreditCardByCif(@PathVariable String cif) {

        List<CreditCard> result = service.queryCreditCard(new CifNumber(cif));

        return DataResponse.ok(result);
    } 
    
    @GetMapping("/card/{cardNo}")
    public ResponseEntity<DataResponse<List<CreditCard>>> queryCreditCardByCardNumber(@PathVariable String cardNo) {

        List<CreditCard> result = service.queryCreditCardByCardNumber(cardNo);

        return DataResponse.ok(result);
    } 
}
