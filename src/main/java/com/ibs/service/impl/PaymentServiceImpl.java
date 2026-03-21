package com.ibs.service.impl;

import com.ibs.config.StubProperties;
import com.ibs.dto.request.PaymentRequest;
import com.ibs.dto.response.Contact;
import com.ibs.dto.response.PaymentResponse;
import com.ibs.entity.TransactionLog;
import com.ibs.mapper.PaymentMapper;
import com.ibs.repository.TransactionLogRepository;
import com.ibs.service.PaymentService;
import com.ibs.util.BankCodeUtils;
import com.ibs.util.DateUtils;
import com.ibs.util.GeneratorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final StubProperties stubProperties;
    private final TransactionLogRepository transactionLogRepository;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public PaymentResponse confirmPayment(PaymentRequest request, String bankCode) {
        log.debug("Processing payment confirmation: transactionId={}, bankCode={}",
                request.getTransactionId(), bankCode);

        if (!BankCodeUtils.isValidBankCode(bankCode)) {
            throw new RuntimeException("Invalid BankCode: " + bankCode);
        }

        int sumOfDigits = BankCodeUtils.sumOfDigits(bankCode);
        log.debug("Sum of digits in BankCode: {}", sumOfDigits);

        String bankBik = GeneratorUtils.generateNumericString(stubProperties.getBank().getBikLength());

        List<String> telecomList = GeneratorUtils.generateTelecomList(sumOfDigits);

        Contact contact = paymentMapper.toContact(
                stubProperties.getBank().getDefaultCompany(),
                telecomList
        );

        LocalDateTime now = DateUtils.now();

        TransactionLog transactionLog = paymentMapper.toTransactionLog(
                request, bankCode, "accepted", now, now
        );
        transactionLogRepository.save(transactionLog);

        log.info("Payment confirmed: transactionId={}, status=accepted", request.getTransactionId());

        return paymentMapper.toResponse(request, bankBik, List.of(contact));
    }

}