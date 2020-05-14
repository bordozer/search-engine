package com.bordozer.searchengine.config;

import com.bordozer.measury.stopwatcher.StopwatchManager;
import com.bordozer.measury.stopwatcher.Stopwatcher;
import com.bordozer.measury.stopwatcher.Watch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.CheckForNull;
import java.lang.annotation.Annotation;
import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopConfiguration {

    public static final Stopwatcher WATCHER = StopwatchManager.instance("watcher");

    @Around("within(com.bordozer.searchengine..*)")
    public Object logStartAndEnd(final ProceedingJoinPoint pjp) throws Throwable {
        if (!hasWatchAnnotation(pjp)) {
            return pjp.proceed();
        }

        final String className = pjp.getTarget().getClass().getCanonicalName();
        final String methodName = pjp.getSignature().getName();
        final String method = className + "." + methodName;

        return WATCHER.measureAndReturn(method, () -> {
            try {
                return pjp.proceed();
            } catch (final Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    private boolean hasWatchAnnotation(final ProceedingJoinPoint pjp) {
        @CheckForNull final Annotation watch = Arrays.stream(pjp.getTarget().getClass().getAnnotations())
                .filter(ann -> ann.annotationType().equals(Watch.class)).findFirst()
                .orElse(null);
        return watch != null;
    }
}
