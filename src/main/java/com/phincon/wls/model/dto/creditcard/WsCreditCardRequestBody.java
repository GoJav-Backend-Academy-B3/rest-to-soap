package com.phincon.wls.model.dto.creditcard;

import com.phincon.wls.model.entity.CardNumber;
import com.phincon.wls.model.entity.CifNumber;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WsCreditCardRequestBody {
    /**
     * Construct CreditCardRqBody by CIF number.
     *
     * This was made to provide readable API.
     * Even it assigns the same value.
     *
     * @param cif the CIF number
     * @return CreditCardRqBody
     */
    public static WsCreditCardRequestBody byCif(CifNumber cif) {
        return new WsCreditCardRequestBody(cif.getCif());
    }

    /**
     * Construct CreditCardRqBody by card number.
     *
     * This was made to provide readable API.
     * Even it assigns the same value.
     *
     * @param cardNumber the card number
     * @return CreditCardRqBody
     */
    public static WsCreditCardRequestBody byCardNumber(CardNumber cardNumber) {
        return new WsCreditCardRequestBody(cardNumber.getCardNumber());
    }

    String cust;
}
