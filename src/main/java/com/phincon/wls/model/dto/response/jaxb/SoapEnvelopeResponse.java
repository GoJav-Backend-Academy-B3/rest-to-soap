package com.phincon.wls.model.dto.response.jaxb;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ToString
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SoapEnvelopeResponse {
    private SoapBodyResponse soapBodyResponse;

    @XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    public SoapBodyResponse getSoapBody() {
        return soapBodyResponse;
    }

    public void setSoapBody(SoapBodyResponse soapBodyResponse) {
        this.soapBodyResponse = soapBodyResponse;
    }
}
