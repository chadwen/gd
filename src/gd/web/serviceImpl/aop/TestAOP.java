package gd.web.serviceImpl.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAOP {

	@Pointcut(value="execution(* gd.web.controller.*.testAdd*(..))")
	public void point1(){}
	
	@Before("point1()")
	public void beforeTestAdd(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Class targetClass = joinPoint.getTarget().getClass();

		System.out.println("----------------------------------------------------------");
		System.out.println("here is the point");
		System.out.println("methodName is "+methodName);
		System.out.println("targetClass is "+targetClass.toString());
		System.out.println("----------------------------------------------------------");
	}
}
