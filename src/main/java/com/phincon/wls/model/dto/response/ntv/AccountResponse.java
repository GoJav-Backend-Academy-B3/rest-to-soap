package com.phincon.wls.model.dto.response.ntv;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phincon.wls.annotation.XmlNative;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountResponse {
    @XmlNative(name = "ACCTNBR")
    private String acctNbr;

    @XmlNative(name = "ACCTTYPE")
    private String acctType;

    @XmlNative(name = "ADDRESS1")
    private String address1;

    @XmlNative(name = "ADDRESS2")
    private String address2;

    @XmlNative(name = "ADDRESS3")
    private String address3;

    @XmlNative(name = "ADDRESS4")
    private String address4;

    @XmlNative(name = "ADDRESS5")
    private String address5;

    @XmlNative(name = "CIF")
    private String cif;

    @XmlNative(name = "CTCOTHER")
    private String ctcOther;

    @XmlNative(name = "DOB")
    private String dob;

    @XmlNative(name = "FAXNBR")
    private String faxNbr;

    @XmlNative(name = "FULLNAME")
    private String fullName;

    @XmlNative(name = "HOMEPH")
    private String homePh;

    @XmlNative(name = "IDENTITY")
    private String identity;

    @XmlNative(name = "KEYPARAM")
    private String keyParam;

    @XmlNative(name = "MAIDENNAME")
    private String maidenName;

    @XmlNative(name = "MOBILEPH")
    private String mobilePh;

    @XmlNative(name = "NPWP")
    private String npwp;

    @XmlNative(name = "OFFICEPH")
    private String officePh;

    @XmlNative(name = "POB")
    private String pob;

    @XmlNative(name = "PRIORITY")
    private String priority;

    @XmlNative(name = "RESP")
    private String resp;

    @XmlNative(name = "ZIPCODE")
    private String zipCode;
}
