package com.phincon.wls.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreditCardRequest {
    CreditCardRequestHeader rqHeader;
    CreditCardRequestBody rqBody;
}
