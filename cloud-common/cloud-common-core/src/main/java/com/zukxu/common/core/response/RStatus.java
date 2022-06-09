package com.zukxu.common.core.response;

import lombok.Getter;

/**
 * <p>
 * 系统请求状态编码
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 10:57
 */
@Getter
public enum RStatus {
    OK(200, "成功"),
    FAIL(5001, "失败"),
    //#1000～1999 区间表示参数错误
    //#2000～2999 区间表示用户错误
    //#3000～3999 区间表示接口异常
    SERVER_SHUTDOWN_ERROR_(5002, "服务异常"),
    CAPTCHA_ERROR_(5003, "验证码错误"),
    BIZ_ERROR_(5005, "业务异常");

    private final Integer code;

    private final String msg;

    RStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
