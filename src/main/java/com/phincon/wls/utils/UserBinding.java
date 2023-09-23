package com.phincon.wls.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

public class UserBinding {
    public static String parseJSON(UserRequest request) throws Exception {
        String user = new ObjectMapper().writeValueAsString(request);
        JsonNode jsonNode = new ObjectMapper().readTree(user);

        // Create a DOM Document
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .newDocument();

        // Create SOAP Envelope
        Element envelope = document.createElement("soap:Envelope");
        envelope.setAttribute("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
        document.appendChild(envelope);

        // Create SOAP Body
        Element body = document.createElement("soap:Body");
        envelope.appendChild(body);

        // Create your specific XML structure based on the JSON data
        Element inqData = document.createElement("inqdata");
        inqData.setAttribute("xmlns", "http://inqdata.wsbeans.iseries/");
        body.appendChild(inqData);

        Element arg0 = document.createElement("arg0");
        inqData.appendChild(arg0);

        Element acctNbr = document.createElement("ACCTNBR");
        Text acctNbrText = document.createTextNode(jsonNode.get("acct_nbr").asText());
        acctNbr.appendChild(acctNbrText);
        arg0.appendChild(acctNbr);

        Element acctType = document.createElement("ACCTTYPE");
        Text acctTypeText = document.createTextNode(jsonNode.get("acct_type").asText());
        acctType.appendChild(acctTypeText);
        arg0.appendChild(acctType);

        // Convert the DOM Document to a String
        StringWriter stringWriter = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(
                new DOMSource(document),  new StreamResult(stringWriter));

        return stringWriter.toString();
    }

    public static User parseXML(String request) throws Exception {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(request)));

        User user = new User();
        Element returnElement = (Element) document.getElementsByTagName("return").item(0);
        user.setAcctNbr(returnElement.getElementsByTagName("ACCTNBR").item(0).getTextContent());
        user.setAcctType(returnElement.getElementsByTagName("ACCTTYPE").item(0).getTextContent());
        user.setAddress1(returnElement.getElementsByTagName("ADDRESS1").item(0).getTextContent());
        user.setAddress2(returnElement.getElementsByTagName("ADDRESS2").item(0).getTextContent());
        user.setAddress3(returnElement.getElementsByTagName("ADDRESS3").item(0).getTextContent());
        user.setAddress4(returnElement.getElementsByTagName("ADDRESS4").item(0).getTextContent());
        user.setAddress5(returnElement.getElementsByTagName("ADDRESS5").item(0).getTextContent());
        user.setCif(returnElement.getElementsByTagName("CIF").item(0).getTextContent());
        user.setCtcOther(returnElement.getElementsByTagName("CTCOTHER").item(0).getTextContent());
        user.setDob(returnElement.getElementsByTagName("DOB").item(0).getTextContent());
        user.setFaxNbr(returnElement.getElementsByTagName("FAXNBR").item(0).getTextContent());
        user.setFullName(returnElement.getElementsByTagName("FULLNAME").item(0).getTextContent());
        user.setHomePh(returnElement.getElementsByTagName("HOMEPH").item(0).getTextContent());
        user.setIdentity(returnElement.getElementsByTagName("IDENTITY").item(0).getTextContent());
        user.setKeyParam(returnElement.getElementsByTagName("KEYPARAM").item(0).getTextContent());
        user.setMaidenName(returnElement.getElementsByTagName("MAIDENNAME").item(0).getTextContent());
        user.setMobilePh(returnElement.getElementsByTagName("MOBILEPH").item(0).getTextContent());
        user.setNpwp(returnElement.getElementsByTagName("NPWP").item(0).getTextContent());
        user.setOfficePh(returnElement.getElementsByTagName("OFFICEPH").item(0).getTextContent());
        user.setPob(returnElement.getElementsByTagName("POB").item(0).getTextContent());
        user.setPriority(returnElement.getElementsByTagName("PRIORITY").item(0).getTextContent());
        user.setResp(returnElement.getElementsByTagName("RESP").item(0).getTextContent());
        user.setZipCode(returnElement.getElementsByTagName("ZIPCODE").item(0).getTextContent());

        return user;
    }
}
