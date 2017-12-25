/*
 * 2017 Hasan COMAK
 */
package net.entrofi.spring.security.jwt.simplejwtauth.security;

import net.entrofi.spring.security.jwt.simplejwtauth.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUserDetails implements UserDetails {

    private final User delegate;

    public JwtUserDetails(final User delegate) {
        if(delegate == null) {
            throw new IllegalArgumentException("Null delegates are not allowed");
        }
        this.delegate = delegate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return delegate.getPassword();
    }

    @Override
    public String getUsername() {
        return delegate.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
