package com.bordozer.searchengine.config;

import com.bordozer.measury.stopwatcher.StopwatchManager;
import com.bordozer.measury.stopwatcher.Stopwatcher;
import com.bordozer.measury.stopwatcher.Watch;
import com.bordozer.measury.stopwatcher.WatchEntryPoint;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SuppressWarnings("PMD.AvoidCatchingThrowable")
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopConfig {

    @Around("@annotation(watchEntryPoint)")
    public Object aroundWatchEntryPoint(final ProceedingJoinPoint pjp, final WatchEntryPoint watchEntryPoint) {
        final Stopwatcher watcher = StopwatchManager.instance(watchEntryPoint.value());
        watcher.reset();
        final Object result = proceed(pjp);
        watcher.buildReportMills();
        return result;
    }

    @Around("@annotation(watch)")
    public Object aroundWatch(final ProceedingJoinPoint pjp, final Watch watch) {
        final Stopwatcher watcher = StopwatchManager.instance(watch.value());
        final String method = pjp.getTarget().getClass().getCanonicalName() + "." + pjp.getSignature().getName();
        return watcher.measureAndReturn(method, () -> proceed(pjp));
    }

    @SneakyThrows
    private Object proceed(final ProceedingJoinPoint pjp) {
        return pjp.proceed();
    }

}
