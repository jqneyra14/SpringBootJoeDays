package pe.joedayz.restapis.utils.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(pe.joedayz.restapis.utils.aop.LogMethodDetails)")
    public void logMethodCall(JoinPoint jp) throws Throwable{
        logger.info("Method called: " + jp.getSignature());
    }

    @Around("@annotation(pe.joedayz.restapis.utils.aop.LogMethodDetails)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms" + " with params " +
                Arrays.toString(joinPoint.getArgs()));

        return proceed;
    }

    @AfterThrowing(pointcut = "@annotation(pe.joedayz.restapis.utils.aop.LogMethodDetails)", throwing = "e")
    public void logException(JoinPoint jp, Exception e) {
        logger.error("Exception in executing : " + jp.getSignature() + " : " + e.getMessage());
    }
}
