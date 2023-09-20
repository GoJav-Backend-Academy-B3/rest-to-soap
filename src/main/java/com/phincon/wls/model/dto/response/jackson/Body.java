package com.phincon.wls.model.dto.response.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Body {
    @JacksonXmlProperty(localName = "inqdataResponse", namespace = "http://inqdata.wsbeans.iseries/")
    private InqData dataResponse;
}
