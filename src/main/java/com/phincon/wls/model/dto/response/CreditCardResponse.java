package com.phincon.wls.model.dto.response;

import java.util.List;

import com.phincon.wls.model.entity.CreditCard;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class CreditCardResponse {
    private CreditCardResponseHeader rsHeader;
    @Singular("card") private List<CreditCard> rsBody;
}
