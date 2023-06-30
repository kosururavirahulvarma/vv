package com.junit.unittesting.logger;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
	private final Logger logger = LoggerFactory.getLogger(Aspect.class);

	// to specify the point cut
	@Pointcut("execution(* com.junit.unittesting.*.*(..)) || execution(* com.junit.unittesting.controller.*.*(..)) ||execution(* com.junit.unittesting.service.*.*(..))||execution(* com.junit.unittesting.repository.*.*(..)) ")
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
