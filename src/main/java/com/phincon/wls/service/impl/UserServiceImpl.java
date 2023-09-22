package com.phincon.wls.service.impl;


import com.phincon.wls.model.dto.request.InqDataRequest;
import com.phincon.wls.model.dto.request.SoapBodyRequest;
import com.phincon.wls.model.dto.request.SoapEnvelopeRequest;
import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.User;
import com.phincon.wls.model.dto.response.jaxb.SoapEnvelopeResponse;
import com.phincon.wls.model.dto.response.jaxb.UserResponse;
import com.phincon.wls.service.UserService;
import com.phincon.wls.utils.CustomNamespacePrefixMapper;
import com.phincon.wls.utils.UserBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${soap.api.url}")
    private String soapApiUrl;

    @Override
    public UserResponse getUser(String accNumber, String accType) throws JAXBException {
        SoapEnvelopeRequest soapEnvelopeRequest = getSoapEnvelopeRequest(accNumber, accType);

        String soapRequestXML = getXmlString(soapEnvelopeRequest);

        String xmlResult = getUserResponseXml(soapRequestXML);

        SoapEnvelopeResponse soapEnvelopeResponse = convertXmlToSoapEnvelopeResponse(xmlResult);

        return soapEnvelopeResponse.getSoapBody().getInqData().getResult();
    }

    @Override
    public User getUserNative(UserRequest request) throws Exception {
        String resultBinding = UserBinding.jsonToSoap(request);
        String resultEntity = getUserResponseXml(resultBinding);
        return UserBinding.parseXML(resultEntity);
    }

    private SoapEnvelopeResponse convertXmlToSoapEnvelopeResponse(String xmlResult) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SoapEnvelopeResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Replace with your XML string
        StringReader reader = new StringReader(xmlResult);
        return (SoapEnvelopeResponse) unmarshaller.unmarshal(reader);
    }

    private String getUserResponseXml(String soapRequestXML) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequestXML, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                soapApiUrl, // Replace with your SOAP service URL
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

        // Set the custom NamespacePrefixMapper
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

        // Assign data to soapEnvelopeRequest
        user.setAcctNbr(accNumber);
        user.setAcctType(accType);
        inqDataRequest.setUserRequest(user);
        soapBodyRequest.setInqDataRequest(inqDataRequest);
        soapEnvelopeRequest.setSoapBodyRequest(soapBodyRequest);
        return soapEnvelopeRequest;
    }
}
