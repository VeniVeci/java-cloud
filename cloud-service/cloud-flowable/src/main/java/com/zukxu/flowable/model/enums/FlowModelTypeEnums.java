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
public enum FlowModelTypeEnums {
    MODEL_TYPE_BPMN(1, "BPMN"),
    MODEL_TYPE_FORM(2, "FORM"),
    MODEL_TYPE_APP(3, "APP"),
    MODEL_TYPE_DECISION_TABLE(4, "DECISION_TABLE"),
    MODEL_TYPE_CMMN(5, "CMMN"),
    MODEL_TYPE_DECISION_SERVICE(6, "DECISION_SERVICE");

    //@formatter:off
    private Integer code;
    private String msg;
    //@formatter:on


    FlowModelTypeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
