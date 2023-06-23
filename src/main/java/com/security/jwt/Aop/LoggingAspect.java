package com.security.jwt.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	// to specify the point cut
	@Pointcut("execution(* com.security.jwt.*.*(..)) || execution(* com.security.jwt.controller.*.*(..)) ||execution(* com.security.jwt.service.*.*(..))||execution(* com.security.jwt.repository.*.*(..)) ")
	public void applicationPackagePointcut() {

	}

	// before invoking the method
	@Before("applicationPackagePointcut()")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("Executing method: {}", joinPoint.getSignature().toShortString());
	}

	// after invoking the method
	@After("applicationPackagePointcut()")
	public void afterAdvice(JoinPoint joinPoint) {
		logger.info("Finished executing method: {}", joinPoint.getSignature().toShortString());
	}

	// After Successful execution
	@AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		logger.info("Method: {} returned: {}", joinPoint.getSignature().toShortString(), result);
	}

	// After throwing Exceptions
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "exception")
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
		logger.error("Exception thrown by method: {}", joinPoint.getSignature().toShortString());
		logger.error("Exception details: ", exception);
	}

	@Around("applicationPackagePointcut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Executing method: {}", joinPoint.getSignature().toShortString());

		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();

		logger.info("Finished executing method: {}. Execution time: {} ms", joinPoint.getSignature().toShortString(),
				(endTime - startTime));
		return result;
	}
}