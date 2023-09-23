package com.phincon.wls.model.dto.response;

import com.phincon.wls.model.dto.request.CreditCardRqHeader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardResponseHeader {
    String timestamp;
    String status;
    String statusDesc;
    int listCount;
    String listKey;
    CreditCardRqHeader rqHeader;
}
