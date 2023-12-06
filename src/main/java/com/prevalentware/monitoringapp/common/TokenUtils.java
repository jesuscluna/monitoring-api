package com.prevalentware.monitoringapp.common;

public class TokenUtils {
    public static String removeBearerPrefix(String tokenWithBearer) {
        return tokenWithBearer.replace("Bearer ", "");
    }

}
