package com.phincon.wls.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardRqHeader {
    String service;
    String traceId;
    String channel;
    String timestamp;
}
