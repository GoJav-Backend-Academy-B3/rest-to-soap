package com.phincon.wls.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.inquiryaccount.WsInquiryAccountResponse;
import com.phincon.wls.model.entity.Account;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.service.impl.InquiryAccountServiceImpl;

//@ExtendWith(MockitoExtension.class)
//@TestInstance(Lifecycle.PER_CLASS)
public class InquiryAccountServiceTest {

//    @Value("${ws.inqacc.url}")
//    private String wsInqaccUrl;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private final InquiryAccountService service = new InquiryAccountServiceImpl();
//
//    private final CifNumber cifNumber = new CifNumber("0001062020");
//
//    private WsInquiryAccountResponse sampleResponse = null;
//
//    @BeforeAll
//    public void setup() {
//        try {
//            InquiryAccountServiceTestData.init();
//            sampleResponse = InquiryAccountServiceTestData.sampleResponse;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Fail the entire test because the resource cannot be loaded.
//            Assertions.fail("Resource load failed.");
//        }
//    }
//
//    @Test
//    @DisplayName("request inquiry account with cif number should return data")
//    public void requestInquiryAccountCIFNumber_data() {
//        CifNumber cif = cifNumber;
//        final String urlTemplate = String.format("%s/INQACCT/{cifNbr}", wsInqaccUrl);
//        Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.eq(WsInquiryAccountResponse.class),
//                Mockito.eq(cif.getCif())))
//                .thenReturn(sampleResponse);
//
//        final List<Account> result = service.inquiryAccount(cif);
//
//        Assertions.assertTrue(CollectionUtils.isEqualCollection(sampleResponse.getDataRaw(), result));
//    }
}
