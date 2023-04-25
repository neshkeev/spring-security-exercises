package com.luxoft.spingsecurity.loginform.security;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true
)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    private final MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;

    public MethodSecurityConfiguration(MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler) {
        this.defaultMethodSecurityExpressionHandler = defaultMethodSecurityExpressionHandler;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }
}
