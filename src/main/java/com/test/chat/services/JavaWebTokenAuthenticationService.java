package com.test.chat.services;

import com.test.chat.models.entities.Admin;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface JavaWebTokenAuthenticationService {
    public static final String JWT_NAME = "Authentication-Token";

    String createJWT(@NonNull Admin admin);

    Optional<Authentication> parseJWT(@NonNull String token);
}
