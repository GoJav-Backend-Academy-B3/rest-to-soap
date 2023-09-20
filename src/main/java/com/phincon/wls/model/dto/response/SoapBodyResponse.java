package com.phincon.wls.model.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ToString
@XmlRootElement(namespace = "http://inqdata.wsbeans.iseries/")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SoapBodyResponse {

    private InqDataResponse inqData;

    @XmlElement(name = "inqdataResponse", namespace = "http://inqdata.wsbeans.iseries/")
    public InqDataResponse getInqData() {
        return inqData;
    }

    public void setInqData(InqDataResponse inqData) {
        this.inqData = inqData;
    }
}
