package com.phincon.wls.model.entity;

import java.util.List;

import com.phincon.wls.model.dto.response.jaxb.AccountResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData1 {
	AccountResponse accountResponse;
	List<Mutasi> accountHistory;
	List<CreditCard> creditCard;
//	List<Account> account;	
//	List<List<Mutasi>> history;
}
