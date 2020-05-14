package com.bordozer.searchengine.config;

import com.bordozer.measury.stopwatcher.StopwatchManager;
import com.bordozer.measury.stopwatcher.Stopwatcher;
import com.bordozer.measury.stopwatcher.Watch;
import com.bordozer.measury.stopwatcher.WatchEntryPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopConfiguration {

    public static final Stopwatcher WATCHER = StopwatchManager.singleton();

    @Around("within(com.bordozer.searchengine..*)")
    public Object logStartAndEnd(final ProceedingJoinPoint pjp) throws Throwable {

        @CheckForNull final Annotation watchEntryPointAnnotation = hasAnnotation(pjp, WatchEntryPoint.class);
        if (watchEntryPointAnnotation != null) {
            final WatchEntryPoint watchEntryPoint = (WatchEntryPoint) watchEntryPointAnnotation;
            final Stopwatcher watcher = StopwatchManager.instance(watchEntryPoint.value());
            watcher.reset();
            final Object result = pjp.proceed();
            watcher.buildReportMills();
            return result;
        }

        @CheckForNull final Annotation watchAnnotation = hasAnnotation(pjp, Watch.class) ;
        if (watchAnnotation != null) {
            final Watch watchEntryPoint = (Watch) watchAnnotation;
            final Stopwatcher watcher = StopwatchManager.instance(watchEntryPoint.value());

            return watcher.measureAndReturn(getName(pjp), () -> {
                try {
                    return pjp.proceed();
                } catch (final Throwable t) {
                    throw new RuntimeException(t);
                }
            });
        }
        return pjp.proceed();
    }

    @Nonnull
    private String getName(ProceedingJoinPoint pjp) {
        return pjp.getTarget().getClass().getCanonicalName() + "." + pjp.getSignature().getName();
    }

    @CheckForNull
    private Annotation hasAnnotation(final ProceedingJoinPoint pjp, final Class<?> aClass) {
         return Arrays.stream(pjp.getTarget().getClass().getAnnotations())
                .filter(ann -> ann.annotationType().equals(aClass)).findFirst()
                .orElse(null);
    }
}
