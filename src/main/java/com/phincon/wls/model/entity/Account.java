package com.phincon.wls.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phincon.wls.namingstrategies.ScreamingCaseStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(ScreamingCaseStrategy.class)
public class Account {
    String accountNbr;
    String productName;
    String productTp;
    String curr;
    String primary;
    String acctStat;
    String atmCard;
}
