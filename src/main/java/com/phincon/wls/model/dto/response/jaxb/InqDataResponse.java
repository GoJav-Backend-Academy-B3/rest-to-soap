package com.phincon.wls.model.dto.response.jaxb;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@ToString
@XmlRootElement
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@XmlType(propOrder = {"result"})
public class InqDataResponse {
    private AccountResponse result;

    @XmlElement(name = "return")
    public AccountResponse getResult() {
        return result;
    }

    public void setResult(AccountResponse result) {
        this.result = result;
    }
}
