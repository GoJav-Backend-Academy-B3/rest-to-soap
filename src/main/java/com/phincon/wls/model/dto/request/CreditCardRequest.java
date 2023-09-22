package com.phincon.wls.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditCardRequest {
    CreditCardRqHeader rqHeader;
    CreditCardRqBody rqBody;
}
