package com.zukxu.common.core.exception;

import com.zukxu.common.core.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author xupu
 * @since 2021-09-15 10:54
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理器
     *
     * @param e Exception
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<?> handlerException(Exception e) {
        log.error("系统异常={}", e.getMessage());
        e.printStackTrace();
        return R.fail();
    }

    /**
     * 自定义异常处理方法
     *
     * @param e BaseException
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public R<?> handlerBaseException(BaseException e) {
        log.error("系统异常===>{}", e.getMessage());
        e.printStackTrace();
        return R.fail().msg(e.getMessage()).code(e.getCode());
    }
    /**-------- 指定异常处理方法 --------**/
    /**
     * 自定义异常处理方法
     *
     * @param e BusinessException
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R<?> handlerBusinessException(BusinessException e) {
        log.error("业务异常===>{}", e.getMessage());
        e.printStackTrace();
        return R.fail().msg(e.getMessage()).code(e.getCode());
    }

}
