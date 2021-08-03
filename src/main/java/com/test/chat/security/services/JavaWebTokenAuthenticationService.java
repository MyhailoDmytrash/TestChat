package com.test.chat.security.services;

import ch.qos.logback.core.net.server.Client;
import com.test.chat.data.models.Authority;
import com.test.chat.data.models.User;
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
public class JavaWebTokenAuthenticationService
{
    protected static final String SECRET = "fS#$@136F";

    public String createJWT(@NonNull final User user)
    {
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(Map.of(
                        "email", user.getEmail(),
                        "password", user.getPassword(),
                        "auth", user.getAuthority().toString()
                ))
                .compact();
    }

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
