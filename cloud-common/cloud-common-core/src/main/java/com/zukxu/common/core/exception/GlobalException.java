package com.zukxu.common.core.exception;

/**
 * 全局异常
 *
 * @author xupu
 * @since 2021-09-15 10:54
 */

public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    private String details;

    public GlobalException() {}

    public GlobalException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public GlobalException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public GlobalException setDetails(String details) {
        this.details = details;
        return this;
    }

}
