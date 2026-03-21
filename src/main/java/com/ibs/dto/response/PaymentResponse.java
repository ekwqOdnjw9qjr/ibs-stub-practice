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
@Schema(description = "Payment confirmation response")
public class PaymentResponse {

    @JsonProperty("transaction_id")
    @Schema(description = "Transaction ID", example = "T-342-67777")
    private String transactionId;

    @JsonProperty("bank_bik")
    @Schema(description = "Bank BIK", example = "2345678997")
    private String bankBik;

    @Schema(description = "Payment status", example = "accepted")
    private String status;

    @Schema(description = "Contact information")
    private List<Contact> contact;
}