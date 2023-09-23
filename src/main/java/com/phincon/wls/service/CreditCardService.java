package com.phincon.wls.service;

import java.util.List;

import com.phincon.wls.model.entity.CardNumber;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.model.entity.CreditCard;

public interface CreditCardService {
    default public List<CreditCard> queryCreditCard(CifNumber cifNumber) {
        return this.queryCreditCard(cifNumber.getCif());
    }

    default public List<CreditCard> queryCreditCard(CardNumber cardNumber) {
        return this.queryCreditCard(cardNumber.getCardNumber());
    }

    public List<CreditCard> queryCreditCard(String number);
}
