package com.phincon.wls.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phincon.wls.annotation.XmlNative;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter @Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@XmlRootElement
@ToString
public class AccountResponse {
    @XmlNative(name = "ACCTNBR")
    @XmlElement(name = "ACCTNBR")
    private String acctNbr;

    @XmlNative(name = "ACCTTYPE")
    @XmlElement(name = "ACCTTYPE")
    private String acctType;
}

