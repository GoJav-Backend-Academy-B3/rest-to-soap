package com.phincon.wls.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.request.CreditCardRequest;
import com.phincon.wls.model.dto.request.CreditCardRequestBuilder;

public class CreditCardServiceTest {

    @Value("${ws.url.creditcard}")
    private String wsCreditCardUrl;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CreditCardService service = new CreditCardServiceImpl();

    // CIF request
    private CreditCardRequest sampleRequest = new CreditCardRequestBuilder()
            .header()
                .service("listCustomerCIFCardSummaryListInput")
                .traceId("ABCDEFHIJKLMNOPQRSTUVWXY7")
                .channel("CC")
                .timestamp("2023-09-19 15:48:57.821").ok()
            .cif("0002917054")
            .build();

    private CreditCardResponse sampleResponse = new CreditCardResponseBuilder()
            .rsHeader()
                .timestamp("2023-09-19-20.43.13.588")
                .status("0000")
                .statusDesc("Success")
                // listCount will be generated after filling the rsBody...
                .listKey("eof*")
                .rqHeader()
                    .service("listCustomerCIFCardSummaryListInput").traceId("ABCDEFHIJKLMNOPQRSTUVWXY7")
                    .channel("CC").timestamp("2023-09-19 15:48:57.821").listKey("").ok()
                .ok()
            .card()
                .nbr("4377001000167283").prd(81).prdCurr("360").prdType("V").aplType("P")
                .sts("2").blk("").inactiveDt("20230907").crLimit(50000000)
                .acStmBal(20080122.58).acPyDueDt("20231003").acPyDueAmt(16654889.13)
                .acCurBal(33856264.81).acAvailCr(16143735.19).acPointBal(238)
                .embName("AN0341349").acLstStmDt("20230913").acLstPyAmt(0)
                .acLstPyDte("00000000").acCTDPyAmt(0).customer("160323730866")
                .cif("0002917054").ok()
            .card()
                .nbr("4377001001587059").prd(81).prdCurr("360").prdType("V").aplType("P")
                .sts("4").blk("E0").inactiveDt("00000000").crLimit(50000000)
                .acStmBal(20080122.58).acPyDueDt("20231003").acPyDueAmt(16654889.13)
                .acCurBal(33856264.81).acAvailCr(16143735.19).acPointBal(238)
                .embName("AN0341349").acLstStmDt("20230913").acLstPyAmt(0)
                .acLstPyDte("00000000").acCTDPyAmt(0).customer("160323730866")
                .cif("0002917054").ok()
            .build();

    @Test
    @DisplayName("Request Credit Card with CIF should return data")
    public void requestCreditCardCIF_data() throws Exception {
        HttpEntity<CreditCardRequest> request = new HttpEntity<>(sampleRequest);
        Mockito.when(restTemplate.exchange(wsCreditCardUrl, HttpMethod.POST, request, CreditCardResponse.class))
            .thenReturn(new ResponseEntity(sampleResponse, HttpStatus.OK));
        
        CreditCardResponse response = service.queryCreditCard(sampleRequest);

        Assertions.assertEquals(response, sampleResponse);
    }
}
