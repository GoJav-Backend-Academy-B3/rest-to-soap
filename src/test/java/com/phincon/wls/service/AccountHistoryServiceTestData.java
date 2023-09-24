package com.phincon.wls.service;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.model.dto.accounthistory.AccountHistoryResponse;

/**
 * Provides Account History Service Test Data.
 *
 * Note: The static function {@code AccountHistoryServiceTestData::init()} must
 * be called
 * in order to get sampleResponse.
 */
public class AccountHistoryServiceTestData {
    public static void init() throws DatabindException, StreamReadException, IOException {
        InputStream is = AccountHistoryServiceTestData.class.getClassLoader()
                .getResourceAsStream("samplemutasiresponse.json");
        ObjectMapper mapper = new ObjectMapper();
        sampleResponse = mapper.readValue(is, AccountHistoryResponse.class);
    }

    public static AccountHistoryResponse sampleResponse = null;
}
