package com.phincon.wls.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mutasi {
    @JsonProperty("TRXDATE")
    private String trxDate;
    @JsonProperty("PSTGDATE")
    private String pstgDate;
    @JsonProperty("TRXCODE")
    private String trxCode;
    @JsonProperty("TRXCODEDSC")
    private String trxCodeDsc;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("CURR")
    private String curr;
    @JsonProperty("TRXAMOUNT")
    private String trxAmount;
    @JsonProperty("LCLCCY")
    private String lclccy;
    @JsonProperty("LCLEQUIVAL")
    private String lclEquival;
    @JsonProperty("DBCR")
    private String dbcr;
    @JsonProperty("BUYEXCH")
    private String buyExch;
    @JsonProperty("SELLEXCH")
    private String sellExch;
    @JsonProperty("REFERENSI")
    private String referensi;
    @JsonProperty("CHKBGNBR")
    private String chkBgnBr;
    @JsonProperty("RUNBAL")
    private String runbal;
    @JsonProperty("EFEKTIF")
    private String efektif;
}
