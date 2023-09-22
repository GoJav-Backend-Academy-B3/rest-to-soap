package com.phincon.wls.model.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    private String acctNbr;
    private String acctType;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String cif;
    private String ctcOther;
    private String dob;
    private String faxNbr;
    private String fullName;
    private String homePh;
    private String identity;
    private String keyParam;
    private String maidenName;
    private String mobilePh;
    private String npwp;
    private String officePh;
    private String pob;
    private String priority;
    private String resp;
    private String zipCode;
}
