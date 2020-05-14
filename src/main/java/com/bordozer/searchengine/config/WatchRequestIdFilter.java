package com.bordozer.searchengine.config;

import com.bordozer.commons.web.RequestIdFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bordozer.searchengine.config.AopConfiguration.WATCHER;

public class WatchRequestIdFilter extends RequestIdFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
        WATCHER.setKey(response.getHeader(HTTP_HEADER_TRACE_ID));
//        filterChain.doFilter(request, response);
    }
}
