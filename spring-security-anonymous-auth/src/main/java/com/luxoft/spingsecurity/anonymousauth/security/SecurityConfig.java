package com.luxoft.spingsecurity.anonymousauth.security;

import com.luxoft.spingsecurity.anonymousauth.model.User;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement()
                .and()
            .authorizeRequests()
                .antMatchers("/login.html", "/login", "/deny.html").permitAll()
                .antMatchers("/user/whoami").permitAll()
                .antMatchers("/company/**", "/user/**").authenticated()
                .antMatchers("/info").hasAuthority("ROLE_ANON")
                .antMatchers("/**").denyAll()
                .and()
            .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureUrl("/deny.html")
                .defaultSuccessUrl("/company", true)
                .and()
            .anonymous()
                .authorities("ROLE_ANON")
                .principal(new UserDetailsAdapter(anonymous()))
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder(10));
    }

    static User anonymous() {
        User user = new User();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            user.setLogin((String) auth.getPrincipal());
            final var authorities = auth.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            user.setRoles(authorities);
        }
        else {
            user.setLogin("anonymous");
            user.setRoles(List.of("ROLE_ANON"));
        }
        return user;
    }
}
