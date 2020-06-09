package com.bordozer.searchengine.config;

import com.bordozer.measury.stopwatcher.StopwatchManager;
import com.bordozer.measury.stopwatcher.Stopwatcher;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bordozer.commons.web.RequestIdFilter.LOG_TRACE_ID;

public class WatchRequestIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        final Stopwatcher stopwatcher = StopwatchManager.forThread();
        stopwatcher.setReportName(MDC.get(LOG_TRACE_ID));
        filterChain.doFilter(request, response);
        stopwatcher.buildReportMills();
    }
}
