package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: logs method execution time for every method in com.library.service.
 * Exercise 8: adds simple "before" and "after" advice methods to demonstrate
 * separating cross-cutting logging concerns from business logic.
 */
@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut: every method in every class under com.library.service
    private static final String SERVICE_METHODS = "execution(* com.library.service.*.*(..))";

    /**
     * Exercise 8: "before" advice - runs before the target method executes.
     */
    @Before(SERVICE_METHODS)
    public void logBefore(JoinPoint joinPoint) {
        log.info(">> Entering: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * Exercise 8: "after" advice - runs after the target method completes
     * (whether it returned normally or threw an exception).
     */
    @After(SERVICE_METHODS)
    public void logAfter(JoinPoint joinPoint) {
        log.info("<< Exiting: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * Exercise 3: "around" advice - wraps the method call so we can measure
     * and log its execution time.
     */
    @Around(SERVICE_METHODS)
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long elapsedMillis = (System.nanoTime() - start) / 1_000_000;
        log.info("[TIMING] {} executed in {} ms", joinPoint.getSignature().toShortString(), elapsedMillis);
        return result;
    }
}
