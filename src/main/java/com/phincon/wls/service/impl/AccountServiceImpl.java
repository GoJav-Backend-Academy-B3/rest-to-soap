package com.phincon.wls.service.impl;


import com.phincon.wls.exception.custom.NotFoundException;
import com.phincon.wls.model.dto.request.AccountRequest;
import com.phincon.wls.model.dto.request.InqDataRequest;
import com.phincon.wls.model.dto.request.SoapBodyRequest;
import com.phincon.wls.model.dto.request.SoapEnvelopeRequest;
import com.phincon.wls.model.dto.response.jaxb.AccountResponse;
import com.phincon.wls.model.dto.response.jaxb.SoapEnvelopeResponse;
import com.phincon.wls.service.AccountService;
import com.phincon.wls.utils.Bind;
import com.phincon.wls.utils.CustomNamespacePrefixMapper;
import com.phincon.wls.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
//@Slf4j
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
    @Autowired
    @Qualifier("config")
    private RestTemplate restTemplate;

    @Value("${soap.api.url}")
    private String soapApiUrl;

    LogUtils logs = new LogUtils();
    
    @Override
    public AccountResponse getAccount(String accNumber, String accType) throws Exception {
        SoapEnvelopeRequest soapEnvelopeRequest = getSoapEnvelopeRequest(accNumber, accType);

        //String soapRequestXML = getXmlString(soapEnvelopeRequest);
        String soapRequestXML = xmlBody(accNumber, accType);

        logs.printLog(soapRequestXML);
        log.debug(soapRequestXML);

        String xmlResult = getUserResponseXml(soapRequestXML);

        logs.printLog(xmlResult);
        log.debug(xmlResult);

        SoapEnvelopeResponse soapEnvelopeResponse = convertXmlToSoapEnvelopeResponse(xmlResult);

        AccountResponse result = soapEnvelopeResponse.getSoapBody().getInqData().getResult();

//        if (result == null) {
//            throw new Exception("not found");
//        }

        return result;
    }

    @Override
    public com.phincon.wls.model.dto.response.ntv.AccountResponse getAccountNative(AccountRequest request) throws Exception {
        String resultBinding = Bind.parseObject(request);
        logs.printLog(resultBinding);
        String resultEntity = getUserResponseXml(resultBinding);
        logs.printLog(resultEntity);
        com.phincon.wls.model.dto.response.ntv.AccountResponse result = Bind.
                parseXML(resultEntity, com.phincon.wls.model.dto.response.ntv.AccountResponse.class);

        if (result.getAcctNbr() == null || result.getAcctType() == null) {
            throw new NotFoundException("Account not found");
        }

        return result;
    }

    private SoapEnvelopeResponse convertXmlToSoapEnvelopeResponse(String xmlResult) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SoapEnvelopeResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        xmlResult = xmlResult.replaceAll("[^a-zA-Z0-9<>:=.+/ \"]", " ");
        log.debug(xmlResult);
        // Replace with your XML string
        StringReader reader = new StringReader(xmlResult);
        SoapEnvelopeResponse result = (SoapEnvelopeResponse) unmarshaller.unmarshal(reader);
        log.debug(result.getSoapBody().getInqData().getResult().toString());
        //return (SoapEnvelopeResponse) unmarshaller.unmarshal(reader);
        return result;
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
        AccountRequest user = new AccountRequest();
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
    
    
    private String xmlBody(String accNo, String accType) {
    	String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:inq=\"http://inqdata.wsbeans.iseries/\">" + 
    			"   <soapenv:Header/><soapenv:Body><inq:inqdata><arg0><ACCTNBR>{acc_no}</ACCTNBR>" + 
    			"            <ACCTTYPE>{acc_type}</ACCTTYPE></arg0></inq:inqdata></soapenv:Body></soapenv:Envelope>";
    	
    	xml = xml.replace("{acc_no}", accNo);
    	xml = xml.replace("{acc_type}", accType);
    	
    	return xml;
    }
    
    @Override
    public AccountResponse getPostAccount(String accNumber, String accType) throws Exception {
        //SoapEnvelopeRequest soapEnvelopeRequest = getSoapEnvelopeRequest(accNumber, accType);

        //String soapRequestXML = getXmlString(soapEnvelopeRequest);
        String soapRequestXML = xmlBody(accNumber, accType);

        logs.printLog(soapRequestXML);
        log.debug(soapRequestXML);
        String xmlResult = getPostUserResponseXml(soapRequestXML);

        logs.printLog(xmlResult);
        log.debug(xmlResult);


        SoapEnvelopeResponse soapEnvelopeResponse = convertXmlToSoapEnvelopeResponse(xmlResult);
        logs.printLog("convertXmlToSoapEnvelopeResponse Done");
        AccountResponse result = soapEnvelopeResponse.getSoapBody().getInqData().getResult();
        logs.printLog("getResult Done");

//        if (result == null) {
//            throw new Exception("not found");
//        }

        return result;
    }
    
    
    private String getPostUserResponseXml(String soapRequestXML) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequestXML, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                soapApiUrl, // Replace with your SOAP service URL
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return responseEntity.getBody();
    }
    
    
    public SoapEnvelopeResponse testConvertXmlToSoapEnvelopeResponse(String xmlResult) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SoapEnvelopeResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Replace with your XML string
        StringReader reader = new StringReader(xmlResult);
        System.out.println("============");
        SoapEnvelopeResponse result = (SoapEnvelopeResponse) unmarshaller.unmarshal(reader);
        System.out.println("-------------");
        return result;
        //return (SoapEnvelopeResponse) unmarshaller.unmarshal(reader);
    }

}
