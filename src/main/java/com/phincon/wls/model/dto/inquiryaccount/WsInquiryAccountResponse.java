package com.phincon.wls.model.dto.inquiryaccount;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phincon.wls.model.entity.Account;
import com.phincon.wls.namingstrategies.ScreamingCaseStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(ScreamingCaseStrategy.class)
public class WsInquiryAccountResponse {
    String count;
    String resp;
    List<Account> dataRaw;
}
