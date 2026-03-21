package com.ibs.dto.response;

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
@Schema(description = "Contact information")
public class Contact {

    @Schema(description = "Company name", example = "HL pay company")
    private String name;

    @Schema(description = "Telecom identifiers", example = "[\"43t5h7\", \"k8fg534\"]")
    private List<String> telecom;
}