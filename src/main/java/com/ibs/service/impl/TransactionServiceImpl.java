package com.ibs.service.impl;

import com.ibs.config.StubProperties;
import com.ibs.repository.TransactionLogRepository;
import com.ibs.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final StubProperties stubProperties;
    private final TransactionLogRepository transactionLogRepository;

    @Override
    @Transactional
    public String deleteTransaction(Long transactionId, String profile) {
        log.debug("Processing transaction deletion: id={}, profile={}", transactionId, profile);

        int delaySeconds = stubProperties.getDelay().getDelayForProfile(profile);

        try {
            Thread.sleep(delaySeconds * 1000L);

                transactionLogRepository.deleteById(transactionId);
                log.info("Transaction deleted from DB: id={}", transactionId);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Sleep interrupted", e);
        }

        log.info("Transaction processed: id={}, delay={}s", transactionId, delaySeconds);

        return "deleted success";
    }



}