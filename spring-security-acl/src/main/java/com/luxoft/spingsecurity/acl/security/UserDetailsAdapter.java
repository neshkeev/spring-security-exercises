package com.luxoft.spingsecurity.acl.security;

import com.luxoft.spingsecurity.acl.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class UserDetailsAdapter implements UserDetails {

    private final User user;

    public UserDetailsAdapter(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // "user" -> "ROLE_USER"
        return user.getRoles().stream()
                .map(String::toUpperCase)
                .map("ROLE_"::concat)
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    // It is better to calculate or persist these flags into DB
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
