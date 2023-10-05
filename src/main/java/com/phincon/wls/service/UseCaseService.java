package com.phincon.wls.service;

import com.phincon.wls.model.entity.ResponseData1;
import com.phincon.wls.model.entity.ResponseData2;

public interface UseCaseService {
    ResponseData1 useCaseData(String number, String accountType) throws Exception;
    ResponseData2 useCaseData2(String cifNumber) throws Exception;
}
