package com.ibs.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Account check response")
public class AccountCheckResponse {

    @Schema(description = "Account number", example = "1234567890")
    private String account;

    @JsonProperty("vip-client")
    @Schema(description = "VIP client flag", example = "false")
    private boolean vipClient;

    @Schema(description = "Blocked status", example = "false")
    private boolean blocked;

    @Schema(description = "INN", example = "1234567890111")
    private String inn;

    @Schema(description = "List of debts")
    private List<Debt> debt;
}