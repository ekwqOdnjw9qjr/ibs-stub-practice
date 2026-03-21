package com.ibs.util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@UtilityClass
public class GeneratorUtils {

    private static final Random RANDOM = new SecureRandom();

    private static final int MIN_DEBT = 500;
    private static final int MAX_DEBT = 10_000;

    private static final String NUMERIC = "0123456789";
    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static final List<String> DEBT_DESCRIPTIONS = List.of(
            "parking", "gkh", "fine", "credit", "tax", "utilities", "loan"
    );

    public static String generateNumericString(int length) {
        return generateString(length, NUMERIC);
    }

    public static String generateAlphanumericString(int length) {
        return generateString(length, ALPHANUMERIC);
    }

    public static List<String> generateTelecomList(int count) {
        List<String> telecoms = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            telecoms.add(generateAlphanumericString(6));
        }
        return telecoms;
    }

    public static String getRandomDebtDescription() {
        return DEBT_DESCRIPTIONS.get(RANDOM.nextInt(DEBT_DESCRIPTIONS.size()));
    }

    public static int generateRandomDebtSum() {
        return MIN_DEBT + RANDOM.nextInt(MAX_DEBT - MIN_DEBT);
    }

    private static String generateString(int length, String chars) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }
}