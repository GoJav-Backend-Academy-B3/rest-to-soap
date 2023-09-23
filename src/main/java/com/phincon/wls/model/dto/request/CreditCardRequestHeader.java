package com.phincon.wls.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardRequestHeader {
    private String service;
    private String traceId;
    private String channel;
    private String timestamp;
}
