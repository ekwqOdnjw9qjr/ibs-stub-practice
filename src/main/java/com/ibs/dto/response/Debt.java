package com.ibs.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Debt information")
public class Debt {

    @Schema(description = "Debt amount", example = "1000")
    private int sum;

    @Schema(description = "Debt description12", example = "parking")
    private String description;
}