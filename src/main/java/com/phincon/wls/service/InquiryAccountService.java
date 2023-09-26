package com.phincon.wls.service;

import java.util.List;

import com.phincon.wls.model.entity.Account;
import com.phincon.wls.model.entity.CifNumber;

public interface InquiryAccountService {
    public List<Account> inquiryAccount(CifNumber cif);
}
