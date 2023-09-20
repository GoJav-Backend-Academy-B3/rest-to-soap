package com.phincon.wls.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter @Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    @XmlElement(name = "ACCTNBR")
    private String acctNbr;

    @XmlElement(name = "ACCTTYPE")
    private String acctType;

    @XmlElement(name = "ADDRESS1")
    private String address1;

    @XmlElement(name = "ADDRESS2")
    private String address2;

    @XmlElement(name = "ADDRESS3")
    private String address3;

    @XmlElement(name = "ADDRESS4")
    private String address4;

    @XmlElement(name = "ADDRESS5")
    private String address5;

    @XmlElement(name = "CIF")
    private String cif;

    @XmlElement(name = "CTCOTHER")
    private String ctcOther;

    @XmlElement(name = "DOB")
    private String dob;

    @XmlElement(name = "FAXNBR")
    private String faxNbr;

    @XmlElement(name = "FULLNAME")
    private String fullName;

    @XmlElement(name = "HOMEPH")
    private String homePh;

    @XmlElement(name = "IDENTITY")
    private String identity;

    @XmlElement(name = "KEYPARAM")
    private String keyParam;

    @XmlElement(name = "MAIDENNAME")
    private String maidenName;

    @XmlElement(name = "MOBILEPH")
    private String mobilePh;

    @XmlElement(name = "NPWP")
    private String npwp;

    @XmlElement(name = "OFFICEPH")
    private String officePh;

    @XmlElement(name = "POB")
    private String pob;

    @XmlElement(name = "PRIORITY")
    private String priority;

    @XmlElement(name = "RESP")
    private String resp;

    @XmlElement(name = "ZIPCODE")
    private String zipCode;

}
