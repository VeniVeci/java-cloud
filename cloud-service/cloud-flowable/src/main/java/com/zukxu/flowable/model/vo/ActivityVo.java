package com.zukxu.flowable.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * ActivityVo
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 18:26
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ActivityVo", description = "节点信息")
public class ActivityVo implements Serializable {

    @ApiModelProperty(value = "taskId")
    private String id;

    @ApiModelProperty(value = "x坐标")
    private double x;

    @ApiModelProperty(value = "y坐标")
    private double y;

    @ApiModelProperty(value = "宽度")
    private double width;

    @ApiModelProperty(value = "高度")
    private double height;

    @ApiModelProperty(value = "说明")
    private String documentation;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "审批人")
    private String approver;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "节点类型")
    private String nodeType;

    @ApiModelProperty(value = "节点状态")
    private String status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date startDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date endDate;

    @ApiModelProperty(value = "耗时")
    private String duration;

    @ApiModelProperty(value = "审批人工号")
    private String approvalNo;

    @ApiModelProperty(value = "流程实例id")
    private String procInsId;

    @ApiModelProperty(value = "定义id")
    private String procDefId;

    @ApiModelProperty(value = "节点id")
    private String taskDefKey;

}
