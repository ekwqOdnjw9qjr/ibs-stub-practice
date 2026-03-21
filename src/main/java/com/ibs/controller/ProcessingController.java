package com.ibs.controller;

import com.ibs.dto.request.AccountCheckRequest;
import com.ibs.dto.request.PaymentRequest;
import com.ibs.dto.response.AccountCheckResponse;
import com.ibs.dto.response.PaymentResponse;
import com.ibs.service.AccountCheckService;
import com.ibs.service.PaymentService;
import com.ibs.service.TransactionService;
import com.ibs.util.DateUtils;
import com.ibs.util.ProfileUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Processing Stub API", description = "API for emulating processing system")
public class ProcessingController {

    private final AccountCheckService accountCheckService;
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final Environment environment;

    @GetMapping("/v2/checkAccount")
    @Operation(summary = "Check account debts", description = "Returns information about client's debts")
    @ApiResponses(value = { @ApiResponse(responseCode = "202", description = "Account checked successfully"),
                            @ApiResponse(responseCode = "400", description = "Invalid parameters")})
    public ResponseEntity<AccountCheckResponse> checkAccount(@Valid AccountCheckRequest request) {

        log.info("GET /v2/checkAccount called with acc: {}, days: {}", request.getAcc(), request.getDays());

        AccountCheckResponse response = accountCheckService.checkAccount(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/v2/payment")
    @Operation(summary = "Confirm payment", description = "Process payment confirmation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment confirmed"),
            @ApiResponse(responseCode = "400", description = "Missing required headers")
    })
    public ResponseEntity<PaymentResponse> confirmPayment(
            @RequestHeader(value = "BankCode", required = true) String bankCode,
            @Valid @RequestBody PaymentRequest paymentRequest) {

        log.info("POST /v2/payment called with BankCode: {}, body: {}", bankCode, paymentRequest);

        PaymentResponse response = paymentService.confirmPayment(paymentRequest, bankCode);

        HttpHeaders headers = new HttpHeaders();
        headers.set("ProcessingTIme", DateUtils.formatForHeader(DateUtils.now()));

        log.debug("Response: {} with headers: {}", response, headers);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @DeleteMapping("/v1/transactions/cleare/{id}")
    @Operation(summary = "Clear transaction", description = "Delete transaction with simulated delay")
    @ApiResponses(value = { @ApiResponse(responseCode = "100", description = "Transaction deleted successfully")
    })
    public ResponseEntity<String> clearTransaction(
            @Parameter(description = "Transaction ID", required = true, example = "1")
            @PathVariable Long id) {

        log.info("DELETE /v1/transactions/cleare/{} called", id);

        String currentProfile = ProfileUtils.getCurrentProfile(environment);

        String result = transactionService.deleteTransaction(id, currentProfile);

        return ResponseEntity
                .status(HttpStatus.CONTINUE)
                .header("Content-Type", "text/plain")
                .body(result);
    }
}