package com.ibs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "stub")
@Getter
@Setter
@Validated
public class StubProperties {

    @NotNull
    private Delay delay = new Delay();

    @NotNull
    private Bank bank = new Bank();

    @Getter
    @Setter
    public static class Delay {

        @Min(0)
        private int defaultDelay = 1;

        private Map<String, @Min(0) Integer> profiles = Map.of();

        public int getDelayForProfile(String profile) {
            return profiles.getOrDefault(profile, defaultDelay);
        }
    }

    @Getter
    @Setter
    public static class Bank {

        @Min(1)
        private int bikLength = 10;

        private String defaultCompany = "HL pay company";
    }

}