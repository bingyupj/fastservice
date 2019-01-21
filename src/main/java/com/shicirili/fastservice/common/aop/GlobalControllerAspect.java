package com.shicirili.fastservice.common.aop;

import com.google.common.base.Stopwatch;
import com.shicirili.fastservice.common.model.APIResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 全局控制器切面
 * 记录日志，记录请求处理时间等信息
 */
@Component
@Aspect
public class GlobalControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAspect.class);

    @Pointcut("execution(* com.shicirili.fastservice.controller..*.*(..)) && !execution(* com.shicirili.fastservice.controller.exception..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("url={} {}",request.getMethod(),request.getRequestURL());
        logger.info("ip={}",request.getRemoteAddr());
        logger.info("method={}",joinPoint.getSignature());
        logger.info("args={}",joinPoint.getArgs());
    }


    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{

        Stopwatch stopwatch = Stopwatch.createStarted();
        logger.info("开始执行Controller: " + pjp.getSignature());
        Object result ;
        try {
            //
            result = pjp.proceed();
            logger.info("正确结束执行Controller: " + pjp.getSignature() + "， 返回值：" + result);
        } catch (Throwable throwable) {
            APIResponse apiResponse = handlerException(pjp, throwable);
            logger.info("错误结束执行Controller: " + pjp.getSignature() + "， 返回值：" + apiResponse.toString());
            throw throwable;
        }
        logger.info("耗时：" + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "(毫秒).");
        return result;
    }

    /*@AfterReturning(pointcut = "pointCut()",returning = "object")
    public void doAfterReturing(Object object){
        logger.info("response:{}",object.toString());
    }*/

    private APIResponse handlerException(ProceedingJoinPoint pjp, Throwable e) {
        return new APIResponse(APIResponse.FAIL,e.getMessage());
    }
}
