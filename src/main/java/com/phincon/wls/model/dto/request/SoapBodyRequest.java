package com.phincon.wls.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@XmlRootElement(namespace = "http://inqdata.wsbeans.iseries/")
@ToString
public class SoapBodyRequest {

    private InqDataRequest inqDataRequest;

    @XmlElement(name = "inqdata", namespace = "http://inqdata.wsbeans.iseries/")
    public InqDataRequest getInqDataRequest() {
        return inqDataRequest;
    }

    public void setInqDataRequest(InqDataRequest inqDataRequest) {
        this.inqDataRequest = inqDataRequest;
    }
}
