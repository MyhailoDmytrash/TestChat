package com.test.chat.services.impl;

import com.test.chat.models.entities.Admin;
import com.test.chat.models.entities.Authority;
import com.test.chat.services.JavaWebTokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JavaWebTokenAuthenticationServiceImpl implements JavaWebTokenAuthenticationService {
    protected static final String SECRET = "fS#$@136F";

    @Override
    public String createJWT(@NonNull final Admin admin)
    {
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(Map.of(
                        "email", admin.getEmail(),
                        "password", admin.getPassword(),
                        "auth", admin.getAuthority().toString()
                ))
                .compact();
    }

    @Override
    public Optional<Authentication> parseJWT(@NonNull final String token)
    {
        try
        {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return Optional.of(new UsernamePasswordAuthenticationToken(
                    claims.get("email"),
                    claims.get("password"),
                    List.of(new Authority((String) claims.get("auth")))));
        }
        catch (Throwable ignore)
        {
            return Optional.empty();
        }
    }

}
