package com.phincon.wls.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @JsonProperty("ACCOUNTNBR")
    String accountNbr;
    @JsonProperty("PRODUCTNAME")
    String productName;
    @JsonProperty("PRODUCTTP")
    String productTp;
    @JsonProperty("CURR")
    String curr;
    @JsonProperty("PRIMARY")
    String primary;
    @JsonProperty("ACCTSTAT")
    String acctStat;
    @JsonProperty("ATMCARD")
    String atmCard;
}
