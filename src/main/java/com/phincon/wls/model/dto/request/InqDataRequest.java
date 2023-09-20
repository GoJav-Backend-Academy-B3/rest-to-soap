package com.phincon.wls.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@XmlRootElement
@ToString
public class InqDataRequest {

    private UserRequest userRequest;

    @XmlElement(name = "arg0")
    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
