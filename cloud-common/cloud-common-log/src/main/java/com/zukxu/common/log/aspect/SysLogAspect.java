package com.zukxu.common.log.aspect;

import com.alibaba.fastjson2.JSON;
import com.zukxu.common.log.model.SysLogOpRecord;
import com.zukxu.common.log.schedule.LogQueue;
import com.zukxu.common.log.util.SysLogUtils;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * <p>
 * 操作日志使用spring event异步入库
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 20:18
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class SysLogAspect {

    private final LogQueue logQueue;

    @Pointcut(" execution(* com.zukxu..*.controller..*.*(..)) ")
    public void requestServer() {}

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        SysLogOpRecord sysLog = SysLogUtils.getSysLog();
        setClassName(sysLog, point);
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        //结合swagger 注解记录日志
        ApiOperation annotation = methodSignature.getMethod().getAnnotation(ApiOperation.class);
        String value = annotation == null ? "" : annotation.value();
        sysLog.setTitle(value);

        logQueue.put(JSON.toJSONString(sysLog));
        return point.proceed();
    }

    private void setClassName(SysLogOpRecord sysLog, ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getMethod().getName();
        log.debug("[类名]:{},[方法]:{}", className, methodName);
        sysLog.setReqContent(sysLog.getReqContent()
                                   .setClassName(className)
                                   .setMethodName(methodName));
    }

}
