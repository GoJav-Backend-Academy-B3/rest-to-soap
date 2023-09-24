package com.phincon.wls.model.dto.creditcard;

import com.phincon.wls.model.entity.CardNumber;
import com.phincon.wls.model.entity.CifNumber;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardRequestBody {
    /**
     * Construct CreditCardRqBody by CIF number.
     *
     * This was made to provide readable API.
     * Even it assigns the same value.
     *
     * @param cif the CIF number
     * @return CreditCardRqBody
     */
    public static CreditCardRequestBody byCif(CifNumber cif) {
        return new CreditCardRequestBody(cif.getCif());
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
    public static CreditCardRequestBody byCardNumber(CardNumber cardNumber) {
        return new CreditCardRequestBody(cardNumber.getCardNumber());
    }

    String cust;
}
