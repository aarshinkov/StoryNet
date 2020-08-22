package com.aarshinkov.web.storynet.aop;

import java.math.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Aspect
@Component
public class LogAspect
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Before("execution(* com.aarshinkov.web.storynet.controllers.*.*(..)) "
          + " || execution(* com.aarshinkov.web.storynet.services.*.*(..))")
  public void methodBegin(JoinPoint joinPoint)
  {
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String methodName = joinPoint.getSignature().getName();
    LOG.debug(className + " -> " + methodName + "() begin --");
  }

  @Around("execution(* com.aarshinkov.web.storynet.controllers.*.*(..)) "
          + " || execution(* com.aarshinkov.web.storynet.services.*.*(..))")
  public Object logExecTime(ProceedingJoinPoint pjp) throws Throwable
  {
    String className = pjp.getTarget().getClass().getSimpleName();
    String methodName = pjp.getSignature().getName();

    long startTime = System.nanoTime();
    Object output = pjp.proceed();
    BigDecimal elapsedTimeMillis = new BigDecimal(System.nanoTime() - startTime).divide(new BigDecimal(1000000));

    String result = className + " -> " + methodName + "() ended in ";

    if (elapsedTimeMillis.doubleValue() < 1000)
    {
      result += elapsedTimeMillis + " millis";
    }
    else
    {
      result += elapsedTimeMillis.divide(new BigDecimal(1000)) + " seconds";
    }

    result += " --";
    LOG.debug(result);

    return output;
  }
}
