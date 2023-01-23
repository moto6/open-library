package io.openlibrary.common.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestIdFilter extends OncePerRequestFilter {
    private static final AtomicLong idGenerator = new AtomicLong();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute("X-Request-ID", idGenerator.incrementAndGet());
        filterChain.doFilter(request, response);
    }
}
