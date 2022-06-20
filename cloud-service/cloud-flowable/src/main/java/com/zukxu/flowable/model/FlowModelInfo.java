package com.zukxu.flowable.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zukxu.common.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ${END}
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 14:14:56
 */

@ApiModel(value = "tbl_flow_model_info")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tbl_flow_model_info")
public class FlowModelInfo extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 模型id
     */
    @TableField(value = "model_id")
    @ApiModelProperty(value = "模型id")
    private String modelId;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "name")
    private String name;

    /**
     * 模型key
     */
    @TableField(value = "model_key")
    @ApiModelProperty(value = "模型key")
    private String modelKey;

    /**
     * 模型类型: 0 自定义流程 1是业务流程
     * {@link com.zukxu.flowable.model.enums.FlowModelTypeEnums}
     */
    @TableField(value = "model_type")
    @ApiModelProperty(value = "模型类型")
    private Integer modelType;

    @TableField(value = "model_icon")
    @ApiModelProperty(value = "modelIcon")
    private String modelIcon;

    /**
     * 表单模型类型: 0 自定义流程 1是业务流程
     * {@link com.zukxu.flowable.model.enums.FlowFormTypeEnums}
     */
    @TableField(value = "form_type")
    @ApiModelProperty(value = "表单模型类型: 0 自定义流程表单 1是业务流程表单 2是任务流程表单")
    private Integer formType;

    /**
     * 系统标识
     */
    @TableField(value = "app_sn")
    @ApiModelProperty(value = "系统标识")
    private String appSn;

    /**
     * 应用名称
     */
    @TableField(exist = false)
    private String appName;

    /**
     * 分类编码
     */
    @TableField(value = "category_code")
    @ApiModelProperty(value = "分类编码")
    private String categoryCode;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 模型发布状态
     * {@link com.zukxu.flowable.model.enums.FlowStatusEnums}
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "模型发布状态")
    private Integer status;

    @TableField(exist = false)
    private String statusName;

    /**
     * 拓展信息 状态
     * {@link com.zukxu.flowable.model.enums.FlowStatusEnums}
     */
    @TableField(value = "extend_status")
    @ApiModelProperty(value = "拓展信息 状态")
    private Integer extendStatus;

    @TableField(exist = false)
    private String extendStatusName;

    /**
     * 所属部门id
     */
    @TableField(value = "own_dept_id")
    @ApiModelProperty(value = "所属部门id")
    private String ownDeptId;

    /**
     * 所属部门名称
     */
    @TableField(value = "own_dept_name")
    @ApiModelProperty(value = "所属部门名称")
    private String ownDeptName;

    /**
     * 流程拥有者ID
     */
    @TableField(value = "flow_owner_id")
    @ApiModelProperty(value = "流程拥有者ID")
    private String flowOwnerId;

    /**
     * 流程拥有者名称
     */
    @TableField(value = "flow_owner_name")
    @ApiModelProperty(value = "流程拥有者名称")
    private String flowOwnerName;

    /**
     * 流程对接人工号
     */
    @TableField(value = "process_docking_id")
    @ApiModelProperty(value = "流程对接人工号")
    private String processDockingId;

    /**
     * 流程对接人名称
     */
    @TableField(value = "process_docking_name")
    @ApiModelProperty(value = "流程对接人名称")
    private String processDockingName;

    /**
     * 适用公司 json id:name
     */
    @TableField(value = "apply_companies")
    @ApiModelProperty(value = "适用公司(多个公司，以逗号隔开)")
    private String applyCompanies;

    /**
     * 授权管理人员
     */
    @TableField(value = "`superuser`")
    @ApiModelProperty(value = "授权管理人员")
    private String superuser;

    /**
     * 流程中心是否显示 1 是 0 否
     * {@link com.zukxu.flowable.constants.FlowConstants#NUM_1}
     * {@link com.zukxu.flowable.constants.FlowConstants#NUM_0}
     */
    @TableField(value = "show_status")
    @ApiModelProperty(value = "流程中心是否显示 1 是 0 否")
    private Integer showStatus;

    /**
     * 适用范围
     */
    @TableField(value = "applied_range")
    @ApiModelProperty(value = "适用范围")
    private Integer appliedRange;

    @TableField(exist = false)
    private String appliedRangeName;

    /**
     * 授权功能
     */
    @TableField(value = "auth_point_list")
    @ApiModelProperty(value = "授权功能")
    private String authPointList;

    /**
     * 业务表单的URL
     */
    @TableField(value = "business_url")
    @ApiModelProperty(value = "业务表单的URL")
    private String businessUrl;

    /**
     * 功能范围(1 允许转办 2允许加签 3允许转阅 4允许打印 5相近节点同一人员自动跳过 可以多选 )
     * {@link com.zukxu.flowable.model.enums.FlowFunctionRangeEnums}
     */
    @TableField(value = "function_range")
    @ApiModelProperty(value = "功能范围(1 允许转办 2允许加签 3允许转阅 4允许打印 5相近节点同一人员自动跳过 可以多选 )")
    private String functionRange;

    /**
     * 跳过设置
     * {@link com.zukxu.flowable.model.enums.FlowTaskSkipSetEnums}
     */
    @TableField(value = "skip_set")
    @ApiModelProperty(value = "跳过设置")
    private Integer skipSet;

    /**
     * 排序
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    @TableField(exist = false)
    private List<String> categoryCodes;

    /**
     * 流程定义ID
     */
    @TableField(exist = false)
    private String processDefinitionId;

    @TableField(exist = false)
    private String modelXml;

    private static final long serialVersionUID = 1L;

}