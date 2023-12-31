package com.phincon.wls.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.phincon.wls.model.dto.creditcard.WsCreditCardRequest;
import com.phincon.wls.model.dto.creditcard.WsCreditCardRequestBody;
import com.phincon.wls.model.dto.creditcard.WsCreditCardRequestHeader;
import com.phincon.wls.model.dto.creditcard.WsCreditCardResponse;
import com.phincon.wls.model.dto.creditcard.WsCreditCardResponseHeader;
import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.service.impl.CreditCardServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {

    @Value("${ws.creditcard.url}")
    private String wsCreditCardUrl;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private final CreditCardService service = new CreditCardServiceImpl();

    private final WsCreditCardRequestHeader rqHeader = WsCreditCardRequestHeader.builder()
            .service("listCustomerCIFCardSummaryListInput")
            .traceId("ABCDEFHIJKLMNOPQRSTUVWXY7")
            .channel("CC")
            .timestamp("2023-09-19 15:48:57.821").build();

    // CIF request
    private final CifNumber cifNumber = new CifNumber("0002917054");
    private final WsCreditCardRequest sampleRequest = WsCreditCardRequest.builder()
            .rqHeader(rqHeader)
            .rqBody(WsCreditCardRequestBody.byCif(cifNumber))
            .build();

    private final WsCreditCardResponse sampleResponse = WsCreditCardResponse.builder()
            .rsHeader(WsCreditCardResponseHeader.builder()
                    .timestamp("2023-09-19-20.43.13.588")
                    .status("0000")
                    .statusDesc("Success")
                    .listCount(2)
                    .listKey("eof*")
                    .rqHeader(rqHeader)
                    .build())
            .card(CreditCard.builder()
                    .cardNbr("4377001000167283").cardPrd(81).cardPrdCurr("360").cardPrdType("V")
                    .cardAplType("P").cardSts("2").cardBlk("").cardInactiveDt("20230907")
                    .cardCrLimit(50000000).cardAcStmBal(20080122.58).cardAcPyDueDt("20231003")
                    .cardAcPyDueAmt(16654889.13).cardAcCurBal(33856264.81).cardAcAvailCr(16143735.19)
                    .cardAcPointBal(238).cardEmbName("AN0341349").cardAcLstStmDt("20230913")
                    .cardAcLstPyAmt(0).cardAcLstPyDte("00000000").cardAcCTDPyAmt(0)
                    .cardCustomer("160323730866").cardCIF("0002917054").build())
            .card(CreditCard.builder()
                    .cardNbr("4377001001587059").cardPrd(81).cardPrdCurr("360").cardPrdType("V")
                    .cardAplType("P").cardSts("4").cardBlk("E0").cardInactiveDt("00000000")
                    .cardCrLimit(50000000).cardAcStmBal(20080122.58).cardAcPyDueDt("20231003")
                    .cardAcPyDueAmt(16654889.13).cardAcCurBal(33856264.81).cardAcAvailCr(16143735.19)
                    .cardAcPointBal(238).cardEmbName("AN0341349").cardAcLstStmDt("20230913")
                    .cardAcLstPyAmt(0).cardAcLstPyDte("00000000").cardAcCTDPyAmt(0)
                    .cardCustomer("160323730866").cardCIF("0002917054").build())
            .build();

    @Test
    @DisplayName("Request Credit Card with CIF should return data")
    public void requestCreditCardCIF_data() throws Exception {
        final HttpEntity<WsCreditCardRequest> request = new HttpEntity<>(sampleRequest);
        Mockito.when(restTemplate.exchange(Mockito.eq(wsCreditCardUrl), Mockito.eq(HttpMethod.POST), Mockito.eq(request),
                Mockito.eq(WsCreditCardResponse.class)))
                .thenReturn(new ResponseEntity<>(sampleResponse, HttpStatus.OK));

        final List<CreditCard> result = service.queryCreditCard(cifNumber);

        Assertions.assertTrue(CollectionUtils.isEqualCollection(sampleResponse.getRsBody(), result));
    }
}
