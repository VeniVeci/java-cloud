package com.zukxu.common.feign.sentinel.handler;

import com.alibaba.csp.sentinel.Tracer;
import com.zukxu.common.core.exception.BaseException;
import com.zukxu.common.core.exception.BusinessException;
import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 * 全局异常处理器结合sentinel 全局异常处理器不能作用在 oauth server
 * <a href="https://gitee.com/log4j/pig/issues/I1M2TJ">https://gitee.com/log4j/pig/issues/I1M2TJ</a>
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 19:45
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnExpression("!'${security.oauth2.client.clientId}'.isEmpty()")
public class GlobalExceptionHandler {

    /**
     * 全局异常
     *
     * @param e the e
     *
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> handleGlobalException(Exception e) {
        log.error("系统异常==>{}:{}", Arrays.stream(e.getStackTrace()).toArray()[0].toString(), e.getMessage());
        // 业务异常交由 sentinel 记录
        Tracer.trace(e);
        return R.fail(e.getLocalizedMessage());
    }

    /**
     * 自定义异常处理方法
     *
     * @param e BaseException
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public R<?> handlerBaseException(BaseException e) {
        log.error("基础系统异常==>{}:{}", Arrays.stream(e.getStackTrace()).toArray()[0].toString(), e.getMessage());
        return R.fail().msg(e.getMessage()).code(Optional.ofNullable(e.getCode()).orElse(RStatus.FAIL.getCode()));
    }

    /**
     * 自定义异常处理方法
     *
     * @param e BusinessException
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R<?> handlerBusinessException(BusinessException e) {
        log.error("业务异常==>{}:{}", Arrays.stream(e.getStackTrace()).toArray()[0].toString(), e.getMessage());
        return R.fail().msg(e.getMessage()).code(Optional.of(e.getCode()).orElse(RStatus.FAIL.getCode()));
    }

    /**
     * 处理业务校验过程中碰到的非法参数异常 该异常基本由{@link Assert}抛出
     *
     * @param e 参数校验异常
     *
     * @return API返回结果对象包装后的错误输出结果
     *
     * @see Assert#hasLength(String, String)
     * @see Assert#hasText(String, String)
     * @see Assert#isTrue(boolean, String)
     * @see Assert#isNull(Object, String)
     * @see Assert#notNull(Object, String)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数,ex==>{}:{}", Arrays.stream(e.getStackTrace()).toArray()[0].toString(), e.getMessage());
        return R.fail(e.getMessage());
    }

    /**
     * AccessDeniedException
     *
     * @param e the e
     *
     * @return R
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<?> handleAccessDeniedException(AccessDeniedException e) {
        String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractAccessDecisionManager.accessDenied", e.getMessage());
        log.warn("拒绝授权异常信息 ex={}", msg);
        return R.fail(e.getLocalizedMessage());
    }

    /**
     * validation Exception
     *
     * @param exception e
     *
     * @return R
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> handleBodyValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        return R.fail(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * validation Exception (以form-data形式传参)
     *
     * @param exception e
     *
     * @return R
     */
    @ExceptionHandler({ BindException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> bindExceptionHandler(BindException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        return R.fail(fieldErrors.get(0).getDefaultMessage());
    }

}
