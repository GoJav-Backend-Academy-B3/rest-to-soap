package com.phincon.wls.model.dto.accounthistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WsAccountHistoryRequest {
    private String acctNbr;
    private String acctTp;
    private String strDate;
    private String endDate;
    private String strIndex;
}
