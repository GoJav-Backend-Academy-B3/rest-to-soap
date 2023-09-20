package com.phincon.wls.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.model.dto.request.UserRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class UserBinding {
    public static String jsonToSoap(UserRequest request) throws Exception {
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
}
