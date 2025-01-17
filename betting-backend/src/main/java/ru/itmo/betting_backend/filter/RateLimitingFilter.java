package ru.itmo.betting_backend.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import ru.itmo.betting_backend.service.RateLimiterService;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RateLimiterService rateLimiterService;

    private String extractUserIdFromToken(String token) {
        return String.valueOf(token.hashCode() << 1);
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
            String token = request.getHeader("Authorization");
            if (token != null) {
                String userId = extractUserIdFromToken(token);
                RateLimiter rateLimiter = rateLimiterService.getRateLimiter(userId);

                try {
                    if (rateLimiter.acquirePermission()) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(429, "Превышен лимит запросов для пользователя " + userId);
                    }
                } catch (RequestNotPermitted e) {
                    response.sendError(429, "Превышен лимит запросов для пользователя " + userId);
                }
            } else {
                filterChain.doFilter(request, response);
            }
    }
}
