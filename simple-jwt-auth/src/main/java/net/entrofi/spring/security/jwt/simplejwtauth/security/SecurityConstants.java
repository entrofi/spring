/*
 * 2017 Hasan COMAK
 */
package net.entrofi.spring.security.jwt.simplejwtauth.security;

public class SecurityConstants {
    public static final String KEY_SALT = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 1296_000_000; // 15 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/signup";
    public static final String LOGIN_URL = "/login";
}
