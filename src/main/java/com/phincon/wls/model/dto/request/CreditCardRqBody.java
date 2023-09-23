package com.phincon.wls.model.dto.request;

import com.phincon.wls.model.entity.CardNumber;
import com.phincon.wls.model.entity.CifNumber;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardRqBody {
    /**
     * Construct CreditCardRqBody by CIF number.
     *
     * This was made to provide readable API.
     * Even it assigns the same value.
     *
     * @param cif the CIF number
     * @return CreditCardRqBody
     */
    public static CreditCardRqBody byCif(CifNumber cif) {
        return new CreditCardRqBody(cif.getCif());
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
    public static CreditCardRqBody byCardNumber(CardNumber cardNumber) {
        return new CreditCardRqBody(cardNumber.getCardNumber());
    }

    String cust;
}
