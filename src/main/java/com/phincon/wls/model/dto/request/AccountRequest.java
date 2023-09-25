package com.phincon.wls.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phincon.wls.annotation.XmlNative;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@XmlRootElement
@ToString
public class AccountRequest {
    @XmlNative(name = "ACCTNBR")
    private String acctNbr;

    @XmlNative(name = "ACCTTYPE")
    private String acctType;

    @XmlElement(name = "ACCTNBR")
    public String getAcctNbr() {
        return acctNbr;
    }

    public void setAcctNbr(String acctNbr) {
        this.acctNbr = acctNbr;
    }

    @XmlElement(name = "ACCTTYPE")
    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
}

