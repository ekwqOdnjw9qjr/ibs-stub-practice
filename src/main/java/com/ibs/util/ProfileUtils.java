package com.ibs.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.env.Environment;

@UtilityClass
public class ProfileUtils {

    public static String getCurrentProfile(Environment environment) {
        String[] profiles = environment.getActiveProfiles();
        return profiles.length > 0 ? profiles[0] : "default";
    }
}