package com.phincon.wls.service;

import java.util.List;

import com.phincon.wls.model.entity.CardNumber;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.model.entity.CreditCard;

public interface CreditCardService {

    /**
     * Queries list of credit card using CIF number.
     *
     * This is equivalent by calling {@code CreditCardService.queryCreditCard(String)}
     *
     * @param cifNumber CIF number
     * @return List of credit card data 
     */
    default public List<CreditCard> queryCreditCard(CifNumber cifNumber) {
        return this.queryCreditCard(cifNumber.getCif());
    }

    /**
     * Queries list of credit card using card number.
     *
     * This is equivalent by calling {@code CreditCardService.queryCreditCard(String)}
     *
     * @param cardNumber Card number
     * @return List of credit card data 
     */
    default public List<CreditCard> queryCreditCard(CardNumber cardNumber) {
        return this.queryCreditCard(cardNumber.getCardNumber());
    }

    /**
     * Queries list of credit card using either CIF or card number.
     *
     * @param number identifier number
     * @return List of credit card data 
     */
    public List<CreditCard> queryCreditCard(String number);
    
    public List<CreditCard> queryCreditCardByCardNumber(String number);
}
