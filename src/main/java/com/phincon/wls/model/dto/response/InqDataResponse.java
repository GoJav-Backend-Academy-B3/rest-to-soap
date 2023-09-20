package com.phincon.wls.model.dto.response;

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
    private UserResponse result;

    @XmlElement(name = "result")
    public UserResponse getResult() {
        return result;
    }

    public void setResult(UserResponse result) {
        this.result = result;
    }
}
