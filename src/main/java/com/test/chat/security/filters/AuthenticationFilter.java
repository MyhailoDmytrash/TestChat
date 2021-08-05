package com.test.chat.security.filters;

import com.test.chat.ChatApplication;
import com.test.chat.services.impl.JavaWebTokenAuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter
{
    protected final JavaWebTokenAuthenticationServiceImpl javaWebTokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Optional.ofNullable(httpServletRequest.getHeader(ChatApplication.JWT_NAME))
                .flatMap(javaWebTokenAuthenticationService::parseJWT)
                .ifPresentOrElse(authority -> SecurityContextHolder.getContext().setAuthentication(authority),
                        () -> SecurityContextHolder.getContext().setAuthentication(null));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
