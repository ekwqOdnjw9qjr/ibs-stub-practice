package com.ibs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request for account check")
public class AccountCheckRequest {

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    @Schema(description = "Account number", example = "1234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String acc;

    @Positive(message = "Days must be positive")
    @Schema(description = "Number of days to check", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private int days;
}