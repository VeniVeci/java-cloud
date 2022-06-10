package com.zukxu.common.core.response;

import cn.hutool.core.util.ObjectUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 通用封装返回类
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 10:43
 */
@Data
@Builder
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
        return genResult(status.getCode(), status.getMsg(), data);
    }

    private static <T> R<T> genResult(int code, String msg, T data) {
        R<T> r = new R<>();
        return r.setCode(code).setMsg(msg).setData(data);
    }

    public boolean isSuccess() {
        return ObjectUtil.isNotEmpty(code) && Objects.equals(RStatus.OK.getCode(), code);
    }

}
