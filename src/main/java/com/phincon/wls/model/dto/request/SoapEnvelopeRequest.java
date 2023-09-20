package com.phincon.wls.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@ToString
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@XmlType(propOrder = {"soapBodyRequest"})
public class SoapEnvelopeRequest {
    private SoapBodyRequest soapBodyRequest;

    @XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    public SoapBodyRequest getSoapBodyRequest() {
        return soapBodyRequest;
    }

    public void setSoapBodyRequest(SoapBodyRequest soapBodyRequest) {
        this.soapBodyRequest = soapBodyRequest;
    }

}
