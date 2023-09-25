package com.phincon.wls.utils;

import com.phincon.wls.model.dto.request.AccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BindTest {
    private AccountRequest userRequest;

    @BeforeEach
    public void setUp() {
        userRequest = new AccountRequest();
        userRequest.setAcctNbr("test");
        userRequest.setAcctType("test");
    }

    @Test
    public void testParseObject_thenCorrect() throws Exception {
        String soapXml = Bind.parseObject(userRequest);

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

    @Test
    public void testParseXml_thenCorrect() throws Exception {
        String responseXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <inqdataResponse xmlns=\"http://inqdata.wsbeans.iseries/\">\n" +
                "      <return>\n" +
                "        <ACCTNBR>01234</ACCTNBR>\n" +
                "        <ACCTTYPE>BANK</ACCTTYPE>\n" +
                "        <ADDRESS1>string</ADDRESS1>\n" +
                "        <ADDRESS2>string</ADDRESS2>\n" +
                "        <ADDRESS3>string</ADDRESS3>\n" +
                "        <ADDRESS4>string</ADDRESS4>\n" +
                "        <ADDRESS5>string</ADDRESS5>\n" +
                "        <CIF>string</CIF>\n" +
                "        <CTCOTHER>string</CTCOTHER>\n" +
                "        <DOB>string</DOB>\n" +
                "        <FAXNBR>string</FAXNBR>\n" +
                "        <FULLNAME>string</FULLNAME>\n" +
                "        <HOMEPH>string</HOMEPH>\n" +
                "        <IDENTITY>string</IDENTITY>\n" +
                "        <KEYPARAM>string</KEYPARAM>\n" +
                "        <MAIDENNAME>string</MAIDENNAME>\n" +
                "        <MOBILEPH>string</MOBILEPH>\n" +
                "        <NPWP>string</NPWP>\n" +
                "        <OFFICEPH>string</OFFICEPH>\n" +
                "        <POB>string</POB>\n" +
                "        <PRIORITY>string</PRIORITY>\n" +
                "        <RESP>string</RESP>\n" +
                "        <ZIPCODE>string</ZIPCODE>\n" +
                "      </return>\n" +
                "    </inqdataResponse>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>\n";

        com.phincon.wls.model.dto.response.ntv.AccountResponse user = Bind.parseXML(
                responseXML, com.phincon.wls.model.dto.response.ntv.AccountResponse.class);

        assertEquals("01234", user.getAcctNbr());
        assertEquals("BANK", user.getAcctType());
    }
}

