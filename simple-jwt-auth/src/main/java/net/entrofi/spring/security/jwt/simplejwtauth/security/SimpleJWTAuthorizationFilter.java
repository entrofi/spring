/*
 * 2017 Hasan COMAK
 */
package net.entrofi.spring.security.jwt.simplejwtauth.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static net.entrofi.spring.security.jwt.simplejwtauth.security.SecurityConstants.HEADER_STRING;
import static net.entrofi.spring.security.jwt.simplejwtauth.security.SecurityConstants.KEY_SALT;
import static net.entrofi.spring.security.jwt.simplejwtauth.security.SecurityConstants.TOKEN_PREFIX;

public class SimpleJWTAuthorizationFilter extends BasicAuthenticationFilter {

    public SimpleJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public SimpleJWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                        AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(HEADER_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = decodeAuth(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }


    private UsernamePasswordAuthenticationToken decodeAuth(HttpServletRequest request) {
        String jwt = request.getHeader(HEADER_STRING);
        Optional<String> user = parseUser(jwt);
        UsernamePasswordAuthenticationToken authenticationToken = null;
        authenticationToken = user.filter(StringUtils::hasText)
                .map(u -> new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()))
                .orElse(authenticationToken);
        return authenticationToken;
    }


    private Optional<String> parseUser(String jwt) {
        String user = Jwts.parser()
                .setSigningKey(KEY_SALT.getBytes())
                .parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return Optional.ofNullable(user);
    }
}
