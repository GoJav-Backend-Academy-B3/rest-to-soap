package com.phincon.wls.model.dto.accounthistory;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phincon.wls.model.entity.Mutasi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WsAccountHistoryResponse {

    @JsonProperty("RESP")
    private String resp;

    @JsonProperty("MOREDTA")
    private String moreDta;

    @JsonProperty("LASTINDEX")
    private String lastIndex;

    @JsonProperty("MUTASI")
    @Singular("mutasi")
    private List<Mutasi> mutasi;
}
