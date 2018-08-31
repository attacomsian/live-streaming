package com.streaming.security;

public class SecurityConstants {
    public static final String SECRET = "l4sTy5aSWcVjCYtoTAb7umjv6a6Rob510KJrmwfxC9KC7R3gVeim";
    public static final long EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000; // 864000000 milliseconds / 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
