package com.zukxu.flowable.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * <p>
 * 流程监听器参数
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tbl_flow_listener_param")
public class FlowListenerParam implements Serializable {

    public static final String TYPE_CLASS = "string";

    public static final String TYPE_EXPRESSION = "expression";

    private String id;

    private String listenerId;

    private String name;

    private String type;

    private String value;

}
