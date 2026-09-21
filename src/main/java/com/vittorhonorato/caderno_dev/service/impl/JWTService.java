package com.vittorhonorato.caderno_dev.service.impl;

import com.vittorhonorato.caderno_dev.service.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTService extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public JWTService(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);

        if (token != null) {
            if (!tokenProvider.isValid(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String subject = tokenProvider.getSubject(token);
            var user = userService.loadUserByUsername(subject);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request,response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerAuthorization == null) {
            return null;
        }

        if (!headerAuthorization.startsWith("Bearer")) {
            return null;
        }

        return headerAuthorization.replace("Bearer ", "");
    }
}
