package com.ibs.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BankCodeUtils {

    public static int sumOfDigits(String bankCode) {
        if (bankCode == null || bankCode.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (char c : bankCode.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }

    public static boolean isValidBankCode(String bankCode) {
        return bankCode != null && !bankCode.trim().isEmpty() && bankCode.matches(".*\\d.*");
    }
}