package com.zukxu.flowable.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 流程监听器实体类
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tbl_flow_listener")
public class FlowListener implements Serializable {

    //@formatter:off
    public static final String TYPE_CLASS = "class";
    public static final String TYPE_EXPRESSION = "expression";
    public static final String TYPE_DELEGATE_EXPRESSION = "delegateExpression";
    public static final String LISTENER_TYPE_TASK = "taskListener";
    public static final String LISTENER_TYPE_EXECUTION = "executionListener";
    //@formatter:on

    //主键
    private String id;

    //类型 java类 表达式  代理表达式
    private String type;

    //名称
    private String name;

    //监听器类型  任务监听还是执行监听
    private String listenerType;

    //值
    private String value;

    //备注
    private String remark;

    @TableField(exist = false)
    private List<FlowListenerParam> flowListenerParamList;

}
