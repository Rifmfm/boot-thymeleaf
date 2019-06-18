package idu.cs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Around("execution(* iducs.springboot.board.service.*Service.*(..))")  // around가 적용될 포인트 컷 명시 (서비스 안에 것들이 실행될 때)
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName() + "()");  // console창에 표시됨
        Object result = pjp.proceed();
        logger.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
		return result;
	}

}
