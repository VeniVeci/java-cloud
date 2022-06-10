package com.zukxu.flowable.model.enums;

import lombok.Getter;

/**
 * <p>
 * 工单范围枚举类
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 14:22:47
 */

@Getter
public enum FlowFunctionRangeEnums {
    ASSIGN(1, "允许转办"),
    ADD_SIGN(2, "允许加签"),
    TURN_READ(3, "允许转阅"),
    PRINT(4, "允许打印"),
    SKIP_NEXT_SAMPLE(5, "相近节点同一人员自动跳过");

    //@formatter:off
    private Integer code;
    private String msg;
    //@formatter:on


    FlowFunctionRangeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
