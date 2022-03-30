package com.zukxu.common.core.exception;


import com.zukxu.common.core.response.RStatus;

/**
 * 业务异常
 *
 * @author xupu
 * @since 2021-09-15 10:57
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 6550529727254990321L;

    private String details;

    //空构造函数，避免反序列化问题
    public BusinessException() {}

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(RStatus status) {
        super(status);
    }

    public String getDetails() {
        return details;
    }

    public BusinessException setDetails(String details) {
        this.details = details;
        return this;
    }

}
