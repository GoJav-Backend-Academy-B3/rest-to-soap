package com.phincon.wls.model.dto.response.jackson;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    @JacksonXmlProperty(localName = "ACCTNBR")
    private String acctNbr;

    @JacksonXmlProperty(localName = "ACCTTYPE")
    private String acctType;

    @JacksonXmlProperty(localName = "ADDRESS1")
    private String address1;

    @JacksonXmlProperty(localName = "ADDRESS2")
    private String address2;

    @JacksonXmlProperty(localName = "ADDRESS3")
    private String address3;

    @JacksonXmlProperty(localName = "ADDRESS4")
    private String address4;

    @JacksonXmlProperty(localName = "ADDRESS5")
    private String address5;

    @JacksonXmlProperty(localName = "CIF")
    private String cif;

    @JacksonXmlProperty(localName = "CTCOTHER")
    private String ctcOther;

    @JacksonXmlProperty(localName = "DOB")
    private String dob;

    @JacksonXmlProperty(localName = "FAXNBR")
    private String faxNbr;

    @JacksonXmlProperty(localName = "FULLNAME")
    private String fullName;

    @JacksonXmlProperty(localName = "HOMEPH")
    private String homePh;

    @JacksonXmlProperty(localName = "IDENTITY")
    private String identity;

    @JacksonXmlProperty(localName = "KEYPARAM")
    private String keyParam;

    @JacksonXmlProperty(localName = "MAIDENNAME")
    private String maidenName;

    @JacksonXmlProperty(localName = "MOBILEPH")
    private String mobilePh;

    @JacksonXmlProperty(localName = "NPWP")
    private String npwp;

    @JacksonXmlProperty(localName = "OFFICEPH")
    private String officePh;

    @JacksonXmlProperty(localName = "POB")
    private String pob;

    @JacksonXmlProperty(localName = "PRIORITY")
    private String priority;

    @JacksonXmlProperty(localName = "RESP")
    private String resp;

    @JacksonXmlProperty(localName = "ZIPCODE")
    private String zipCode;
}
