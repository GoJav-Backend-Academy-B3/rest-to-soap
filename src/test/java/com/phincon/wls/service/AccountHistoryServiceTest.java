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

import com.phincon.wls.model.dto.accounthistory.AccountHistoryRequest;
import com.phincon.wls.model.dto.accounthistory.AccountHistoryResponse;
import com.phincon.wls.model.entity.Mutasi;
import com.phincon.wls.service.impl.AccountHistoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AccountHistoryServiceTest {

    @Value("${ws.accthst.url}")
    private String wsAccthstUrl;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private final AccountHistoryService service = new AccountHistoryServiceImpl();

    private AccountHistoryRequest sampleRequest = AccountHistoryRequest.builder()
            .acctNbr("1002521739").acctTp("20").strDate("01092023")
            .endDate("18092023").strIndex("0").build();

    private AccountHistoryResponse sampleResponse = null;

    @BeforeAll
    public void setup() {
        try {
            AccountHistoryServiceTestData.init();
            sampleResponse = AccountHistoryServiceTestData.sampleResponse;
        } catch (Exception e) {
            e.printStackTrace();
            // Fail the entire test because the resource cannot be loaded.
            Assertions.fail("Resource load failed.");
        }
    }

    @Test
    @DisplayName("Request account history with required data should return data")
    public void requestAccountHistoryData_data() throws Exception {
        AccountHistoryRequest request = sampleRequest;
        final String urlTemplate = String.format("%s/ACCTHST/{ACCTNBR}/{ACCTTP}/{STRDATE}/{ENDDATE}/{STRINDEX}",
                wsAccthstUrl);
        Mockito.when(restTemplate.getForObject(urlTemplate, AccountHistoryResponse.class, request.getAcctNbr(),
                request.getAcctTp(), request.getStrDate(), request.getEndDate(), request.getStrIndex()))
                .thenReturn(sampleResponse);

        final List<Mutasi> result = service.queryAccountHistory(request);

        Assertions.assertTrue(CollectionUtils.isEqualCollection(sampleResponse.getMutasi(), result));
    }
}
