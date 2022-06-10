package com.zukxu.flowable.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 节点跳过枚举类
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 15:12
 */
@Getter
public enum FlowTaskSkipSetEnums {
    NO_SKIP(1, "不跳过"),
    ADJOIN_SKIP(2, "相邻任务节点跳过"),
    REPEAT_SKIP(3, "重复任务跳过");

    //@formatter:off
    private Integer code;
    private String msg;
    //@formatter:on

    FlowTaskSkipSetEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<Integer, String> getMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(FlowTaskSkipSetEnums::getCode, FlowTaskSkipSetEnums::getMsg));
    }
}
