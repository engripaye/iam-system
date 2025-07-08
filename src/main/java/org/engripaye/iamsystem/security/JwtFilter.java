package org.engripaye.iamsystem.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends GenericFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")) {
            try {
                String token = header.substring(7);
                String username = jwtUtil.getUsername(token);
                String role = jwtUtil.getRole(token);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.singleton(() -> "ROLE_" + role));

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JwtException e) {
            }
        }
        chain.doFilter(req, res);
    }

}
