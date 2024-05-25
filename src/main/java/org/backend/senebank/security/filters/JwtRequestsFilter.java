package org.backend.senebank.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.senebank.dto.response.ErrorResponse;
import org.backend.senebank.security.user.UserDetailsImpl;
import org.backend.senebank.security.user.UserDetailsServiceImpl;
import org.backend.senebank.services.Impl.JwtServiceImpl;
import org.backend.senebank.utils.WebUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestsFilter extends OncePerRequestFilter {
    private final WebUtils webUtils;
    private final JwtServiceImpl jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String email = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            boolean isValid = jwtService.validateToken(jwt);
            if (!isValid) {
                webUtils.sendJsonResponse(response, 401, new ErrorResponse("Invalid token"));
                return;
            }
            email = jwtService.getEmail(jwt);
        }

        if (email != null) {
            UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}