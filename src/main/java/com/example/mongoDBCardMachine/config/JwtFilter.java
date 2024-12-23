package com.example.mongoDBCardMachine.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.mongoDBCardMachine.entity.User;
import com.example.mongoDBCardMachine.entity.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            if (StringUtils.hasText(token)) {
                try {
                    String username;
                    try {
                        username = jwtService.validateToken(token);
                    } catch (MalformedJwtException e) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Неверный JWT токен: " + e.getMessage());
                        return;
                    } catch (ExpiredJwtException e) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Токен истек: " + e.getMessage());
                        return;
                    }
                    User account = userRepository.findByUsername(username).orElseThrow(() ->
                            new RuntimeException("Пользователь с таким именем %s не найден!".formatted(username)));
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    account,
                                    null,
                                    account.getAuthorities()
                            ));
                } catch (JWTVerificationException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неправильный токен! " + e.getMessage());
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
