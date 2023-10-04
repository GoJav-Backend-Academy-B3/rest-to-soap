package com.phincon.wls.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.phincon.wls.model.entity.CifNumber;
import com.phincon.wls.model.entity.CreditCard;
import com.phincon.wls.service.CreditCardService;

//@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean(answer = Answers.CALLS_REAL_METHODS)
//    private CreditCardService service;
//
//    @Autowired
//    private CreditCardController controller;
//
//    @Test
//    @Order(Integer.MIN_VALUE)
//    public void isUserControllerInjected() {
//        Assertions.assertNotNull(controller);
//    }
//
//    @Test
//    @DisplayName("POST request to /creditcard/cif should return credit cards")
//    public void requestPOSTCreditcardCif_CreditCards() throws Exception {
//        List<CreditCard> cards = Arrays.asList(
//                CreditCard.builder()
//                        .cardNbr("4377001000167283").cardPrd(81).cardPrdCurr("360").cardPrdType("V")
//                        .cardAplType("P").cardSts("2").cardBlk("").cardInactiveDt("20230907")
//                        .cardCrLimit(50000000).cardAcStmBal(20080122.58).cardAcPyDueDt("20231003")
//                        .cardAcPyDueAmt(16654889.13).cardAcCurBal(33856264.81).cardAcAvailCr(16143735.19)
//                        .cardAcPointBal(238).cardEmbName("AN0341349").cardAcLstStmDt("20230913")
//                        .cardAcLstPyAmt(0).cardAcLstPyDte("00000000").cardAcCTDPyAmt(0)
//                        .cardCustomer("160323730866").cardCIF("0002917054").build(),
//                CreditCard.builder()
//                        .cardNbr("4377001001587059").cardPrd(81).cardPrdCurr("360").cardPrdType("V")
//                        .cardAplType("P").cardSts("4").cardBlk("E0").cardInactiveDt("00000000")
//                        .cardCrLimit(50000000).cardAcStmBal(20080122.58).cardAcPyDueDt("20231003")
//                        .cardAcPyDueAmt(16654889.13).cardAcCurBal(33856264.81).cardAcAvailCr(16143735.19)
//                        .cardAcPointBal(238).cardEmbName("AN0341349").cardAcLstStmDt("20230913")
//                        .cardAcLstPyAmt(0).cardAcLstPyDte("00000000").cardAcCTDPyAmt(0)
//                        .cardCustomer("160323730866").cardCIF("0002917054").build());
//        CifNumber cifNumber = new CifNumber("0002917054");
//        Mockito.when(service.queryCreditCard(Mockito.eq(cifNumber.getCif()))).thenReturn(cards);
//
//        ResultActions result = mockMvc
//                .perform(MockMvcRequestBuilders.get("/v1/creditcard/cif/{cif}", cifNumber.getCif()));
//
//        result.andExpectAll(
//                MockMvcResultMatchers.status().isOk(),
//                MockMvcResultMatchers.jsonPath("$.data").exists(),
//                MockMvcResultMatchers.jsonPath("$.data").isArray());
//        Mockito.verify(service, Mockito.times(1)).queryCreditCard(cifNumber);
//    }
}
