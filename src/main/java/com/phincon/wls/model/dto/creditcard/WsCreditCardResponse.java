package com.phincon.wls.model.dto.creditcard;

import java.util.List;

import com.phincon.wls.model.entity.CreditCard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WsCreditCardResponse {
    private WsCreditCardResponseHeader rsHeader;
    @Singular("card") private List<CreditCard> rsBody;
}
