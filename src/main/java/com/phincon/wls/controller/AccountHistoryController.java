package com.phincon.wls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.wls.model.dto.accounthistory.WsAccountHistoryRequest;
import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import com.phincon.wls.model.entity.Mutasi;
import com.phincon.wls.service.AccountHistoryService;

@RestController
@RequestMapping("v1/accthst")
public class AccountHistoryController {

    @Autowired
    private AccountHistoryService service;

    @GetMapping("/{acctnbr}/{accttp}/{startdate}/{enddate}/{strindex}")
    public ResponseEntity<DataResponse<List<Mutasi>>> getAccountHistory(@PathVariable String acctnbr,
            @PathVariable String accttp, @PathVariable String starttdate, @PathVariable String enddate,
            @PathVariable String strindex) {
        final WsAccountHistoryRequest accountHistoryRequest = new WsAccountHistoryRequest(acctnbr, accttp, starttdate,
                enddate, strindex);
        List<Mutasi> mutasis = service.queryAccountHistory(accountHistoryRequest);

        return DataResponse.ok(mutasis);
    }
}
