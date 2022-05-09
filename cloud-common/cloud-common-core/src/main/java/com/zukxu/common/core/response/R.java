package com.zukxu.common.core.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 通用封装返回类
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 10:43
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;

    private R() {}

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return genResult(RStatus.OK, data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return genResult(RStatus.OK.getCode(), msg, data);
    }

    public static <T> R<T> fail() {
        return genResult(RStatus.FAIL, null);
    }

    public static <T> R<T> fail(String msg) {
        return genResult(RStatus.FAIL.getCode(), msg, null);
    }

    public static <T> R<T> fail(RStatus r) {
        return genResult(r, null);
    }

    private static <T> R<T> genResult(RStatus status, T data) {
        R<T> r = new R<>();
        r.setCode(status.getCode());
        r.setMsg(status.getMsg());
        r.setData(data);
        return r;
    }

    private static <T> R<T> genResult(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public R<T> code(int code) {
        this.setCode(code);
        return this;
    }

    public R<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public R<T> data(T data) {
        this.setData(data);
        return this;
    }
}
