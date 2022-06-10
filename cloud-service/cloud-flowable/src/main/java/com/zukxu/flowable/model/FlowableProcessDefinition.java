package com.zukxu.flowable.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "act_re_procdef")
public class FlowableProcessDefinition implements Serializable {

    /**
     * id
     */
    @TableId("id_")
    @ApiModelProperty(value = "id")
    private String id;


    /**
     * 分类
     */
    @TableField("category_")
    @ApiModelProperty(value = "分类")
    private String category;

    /**
     * 名称
     */
    @TableField("name_")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 流程定义key
     */
    @TableField("key_")
    @ApiModelProperty(value = "流程定义key")
    private String key;

    /**
     * 部署id
     */
    @TableField("deployment_id_")
    @ApiModelProperty(value = "部署id")
    private String deploymentId;

}
