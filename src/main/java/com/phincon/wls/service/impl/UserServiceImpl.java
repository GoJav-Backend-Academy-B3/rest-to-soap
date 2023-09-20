package com.phincon.wls.service.impl;

import com.phincon.wls.model.dto.request.InqDataRequest;
import com.phincon.wls.model.dto.request.SoapBodyRequest;
import com.phincon.wls.model.dto.request.SoapEnvelopeRequest;
import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.SoapEnvelopeResponse;
import com.phincon.wls.model.dto.response.UserResponse;
import com.phincon.wls.service.UserService;
import com.phincon.wls.utils.CustomNamespacePrefixMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Override
    public UserResponse getUser(String accNumber, String accType) throws JAXBException {
        SoapEnvelopeRequest soapEnvelopeRequest = getSoapEnvelopeRequest(accNumber, accType);

        String soapRequestXML = getXmlString(soapEnvelopeRequest);

        // Print or use the XML string as needed
        System.out.println(soapRequestXML);

        String xmlResult = getUserReponseXml(soapRequestXML);

        System.out.println(xmlResult);

        SoapEnvelopeResponse soapEnvelopeResponse = convertXmlToSoapEnvelopeResponse(xmlResult);

        System.out.println(soapEnvelopeResponse);
        System.out.println(soapEnvelopeResponse.getSoapBody().getInqData().getResult());

        return soapEnvelopeResponse.getSoapBody().getInqData().getResult();
//        return null;
    }

    private SoapEnvelopeResponse convertXmlToSoapEnvelopeResponse(String xmlResult) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SoapEnvelopeResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Replace with your XML string
        StringReader reader = new StringReader(xmlResult);
        return (SoapEnvelopeResponse) unmarshaller.unmarshal(reader);
    }

    private String getUserReponseXml(String soapRequestXML) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequestXML, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://localhost:8084/soap", // Replace with your SOAP service URL
                requestEntity,
                String.class
        );

        return responseEntity.getBody();
    }

    private String getXmlString(SoapEnvelopeRequest soapEnvelopeRequest) throws JAXBException {
        // Create a JAXB context and marshal to XML
        JAXBContext context = JAXBContext.newInstance(SoapEnvelopeRequest.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Remove the standalone attribute
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        // Set the custom NamespacePrefixMapper to rename "ns2" to "soap"
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new CustomNamespacePrefixMapper());

        // Create a StringWriter to capture the XML
        StringWriter stringWriter = new StringWriter();

        // Marshal the data to the StringWriter
        marshaller.marshal(soapEnvelopeRequest, stringWriter);

        // Get the XML as a string
        return stringWriter.toString();
    }

    private SoapEnvelopeRequest getSoapEnvelopeRequest(String accNumber, String accType) {

        // Create an instance of SoapEnvelope and set data
        UserRequest user = new UserRequest();
        SoapEnvelopeRequest soapEnvelopeRequest = new SoapEnvelopeRequest();
        SoapBodyRequest soapBodyRequest = new SoapBodyRequest();
        InqDataRequest inqDataRequest = new InqDataRequest();

        // Assign data to envelope
        user.setAcctNbr(accNumber);
        user.setAcctType(accType);
        inqDataRequest.setUserRequest(user);
        soapBodyRequest.setInqDataRequest(inqDataRequest);
        soapEnvelopeRequest.setSoapBodyRequest(soapBodyRequest);
        return soapEnvelopeRequest;
    }
}
