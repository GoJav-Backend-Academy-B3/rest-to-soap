package com.phincon.wls.service;

import java.util.List;

import com.phincon.wls.model.dto.accounthistory.WsAccountHistoryRequest;
import com.phincon.wls.model.entity.Mutasi;

public interface AccountHistoryService {
    public List<Mutasi> queryAccountHistory(WsAccountHistoryRequest request);
}
