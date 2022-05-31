package com.zukxu.common.core.exception;

import com.zukxu.common.core.response.RStatus;

/**
 * <p>
 * 基础异常
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 15:24
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, Integer code, String defaultMessage, Object[] args) {
        this.module = module;
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.args = args;
    }

    public BaseException(String module, Integer code, Object[] args) {
        this(module, code, null, args);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, defaultMessage, null);
    }

    public BaseException(Integer code, Object[] args) {
        this(null, code, null, args);
    }

    public BaseException(String defaultMessage) {
        this(null, null, defaultMessage, null);
    }

    public BaseException(RStatus rStatus) {
        this(null, rStatus.getCode(), rStatus.getMsg(), null);
    }

    public BaseException() {}

    @Override
    public String getMessage() {
        return defaultMessage;
    }

    public String getModule() {
        return module;
    }

    public Integer getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

}
