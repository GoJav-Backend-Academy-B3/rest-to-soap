package com.phincon.wls.model.dto.inquiryaccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phincon.wls.model.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WsInquiryAccountResponse {
    @JsonProperty("COUNT")
    String count;
    @JsonProperty("RESP")
    String resp;
    @JsonProperty("DATARAW")
    List<Account> dataRaw;
}
