package com.ibs.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Payment confirmation request")
public class PaymentRequest {

    @NotBlank(message = "Transaction ID is required")
    @JsonProperty("transaction_id")
    @Schema(description = "Transaction ID", example = "T-342-67777", requiredMode = Schema.RequiredMode.REQUIRED)
    private String transactionId;

    @Positive(message = "Sum must be positive")
    @Schema(description = "Payment amount", example = "133.12", requiredMode = Schema.RequiredMode.REQUIRED)
    private double sum;

    @JsonProperty("need_processing")
    @Schema(description = "Processing flag", example = "true")
    private boolean needProcessing;
}