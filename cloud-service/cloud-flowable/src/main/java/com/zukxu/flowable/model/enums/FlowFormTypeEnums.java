package com.zukxu.flowable.model.enums;

import lombok.Getter;

/**
 * <p>
 * modelType
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 14:22:47
 */

@Getter
public enum FlowFormTypeEnums {
    CUS_FORM_TYPE(0, "自定义流程表单"),
    BIZ_FORM_TYPE(1, "业务流程表单"),
    TASK_FORM_TYPE(2, "任务流程表单");

    //@formatter:off
    private Integer code;
    private String msg;
    //@formatter:on


    FlowFormTypeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
