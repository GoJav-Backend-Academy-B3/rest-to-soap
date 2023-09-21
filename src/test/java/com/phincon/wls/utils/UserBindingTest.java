package com.phincon.wls.utils;

import com.phincon.wls.model.dto.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserBindingTest {
    private UserRequest userRequest;

    @BeforeEach
    public void setUp() {
        userRequest = new UserRequest();
        userRequest.setAcctNbr("test");
        userRequest.setAcctType("test");
    }

    @Test
    public void testJsonToSoap_thenCorrect() throws Exception {
        String soapXml = UserBinding.jsonToSoap(userRequest);

        String expectedSoapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
                + "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<inqdata xmlns=\"http://inqdata.wsbeans.iseries/\">"
                + "<arg0>"
                + "<ACCTNBR>test</ACCTNBR>"
                + "<ACCTTYPE>test</ACCTTYPE>"
                + "</arg0>"
                + "</inqdata>"
                + "</soap:Body>"
                + "</soap:Envelope>";

        assertEquals(expectedSoapXml, soapXml);
    }
}

