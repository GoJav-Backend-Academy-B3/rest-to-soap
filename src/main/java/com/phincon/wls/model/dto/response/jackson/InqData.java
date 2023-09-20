package com.phincon.wls.model.dto.response.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class InqData {
    @JacksonXmlProperty(localName = "return")
    private User user;
}
