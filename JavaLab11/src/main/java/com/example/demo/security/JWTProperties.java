package com.example.demo.security;

/**
 * @author : Calin Irina, I2E2
 */

public class JWTProperties {
    public static final String SECRET = "ULTRA_SECRET";
    public static final int EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
