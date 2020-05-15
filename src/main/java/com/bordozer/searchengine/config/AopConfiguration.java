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

import javax.annotation.CheckForNull;
import java.lang.annotation.Annotation;
import java.util.Arrays;

@SuppressWarnings("PMD.AvoidCatchingThrowable")
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopConfiguration {

    public static final Stopwatcher WATCHER = StopwatchManager.singleton();

    @Around("within(com.bordozer.searchengine..*)")
    public Object logStartAndEnd(final ProceedingJoinPoint pjp) {

        @CheckForNull final Annotation watchEntryPointAnnotation = getAnnotation(pjp, WatchEntryPoint.class);
        if (watchEntryPointAnnotation != null) {
            final WatchEntryPoint watchEntryPoint = (WatchEntryPoint) watchEntryPointAnnotation;
            final Stopwatcher watcher = StopwatchManager.instance(watchEntryPoint.value());
            watcher.reset();
            final Object result = proceed(pjp);
            watcher.buildReportMills();
            return result;
        }

        @CheckForNull final Annotation watchAnnotation = getAnnotation(pjp, Watch.class);
        if (watchAnnotation != null) {
            final Watch watchEntryPoint = (Watch) watchAnnotation;
            final Stopwatcher watcher = StopwatchManager.instance(watchEntryPoint.value());

            return watcher.measureAndReturn(getName(pjp), () -> {
                return proceed(pjp);
            });
        }
        return proceed(pjp);
    }

    @SneakyThrows
    private Object proceed(final ProceedingJoinPoint pjp) {
        return pjp.proceed();
    }

    @CheckForNull
    private Annotation getAnnotation(final ProceedingJoinPoint pjp, final Class<?> aClass) {
        return Arrays.stream(pjp.getTarget().getClass().getAnnotations())
                .filter(ann -> ann.annotationType().equals(aClass)).findFirst()
                .orElse(null);
    }

    private String getName(final ProceedingJoinPoint pjp) {
        return pjp.getTarget().getClass().getCanonicalName() + "." + pjp.getSignature().getName();
    }
}
