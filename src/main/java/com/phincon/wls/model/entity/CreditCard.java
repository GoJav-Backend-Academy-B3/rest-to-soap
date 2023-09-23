package com.phincon.wls.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {
    String cardNbr;
    int cardPrd;
    String cardPrdCurr;
    String cardPrdType;
    String cardAplType;
    String cardSts;
    String cardBlk;
    String cardInactiveDt;
    long cardCrLimit;
    double cardAcStmBal;
    String cardAcPyDueDt;
    double cardAcPyDueAmt;
    double cardAcCurBal;
    double cardAcAvailCr;
    long cardAcPointBal;
    String cardEmbName;
    String cardAcLstStmDt;
    long cardAcLstPyAmt;
    String cardAcLstPyDte;
    long cardAcCTDPyAmt;
    String cardCustomer;
    String cardCif;
}
