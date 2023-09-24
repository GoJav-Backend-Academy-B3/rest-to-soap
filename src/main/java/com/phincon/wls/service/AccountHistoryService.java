package com.phincon.wls.service;

import java.util.List;

import com.phincon.wls.model.dto.accounthistory.AccountHistoryRequest;
import com.phincon.wls.model.entity.Mutasi;

public interface AccountHistoryService {
    public List<Mutasi> queryAccountHistory(AccountHistoryRequest request);
}
