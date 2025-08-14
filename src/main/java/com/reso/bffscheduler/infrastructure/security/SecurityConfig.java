package com.reso.bffscheduler.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT")
public class SecurityConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";
}
