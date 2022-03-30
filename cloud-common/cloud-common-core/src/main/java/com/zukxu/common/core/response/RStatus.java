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
    OK(200,"成功"),
    FAIL(500,"失败");
    private int code;
    private String msg;

    RStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
