package com.ibs.service.impl;

import com.ibs.dto.request.AccountCheckRequest;
import com.ibs.dto.response.AccountCheckResponse;
import com.ibs.dto.response.Debt;
import com.ibs.mapper.AccountCheckMapper;
import com.ibs.service.AccountCheckService;
import com.ibs.util.GeneratorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountCheckServiceImpl implements AccountCheckService {

    private final AccountCheckMapper accountCheckMapper;

    @Override
    public AccountCheckResponse checkAccount(AccountCheckRequest request) {
        log.debug("Processing account check for acc: {}, days: {}", request.getAcc(), request.getDays());

        boolean isVip = isVipClient(request.getAcc());

        String inn = request.getAcc() + "111";

        List<Debt> debts = generateDebts(request.getDays());

        log.info("Account check completed: acc={}, isVip={}, debtsCount={}",
                request.getAcc(), isVip, debts.size());

        return accountCheckMapper.toResponse(request.getAcc(), isVip, inn, debts);
    }

    private boolean isVipClient(String acc) {
        char lastChar = acc.charAt(acc.length() - 1);
        return (lastChar - '0') % 2 != 0;
    }

    private List<Debt> generateDebts(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> Debt.builder()
                        .sum(GeneratorUtils.generateRandomDebtSum())
                        .description(GeneratorUtils.getRandomDebtDescription())
                        .build()).toList();
    }
}