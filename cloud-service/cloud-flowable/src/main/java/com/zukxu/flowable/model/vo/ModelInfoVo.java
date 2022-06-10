package com.zukxu.flowable.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * flowable模型VO
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 10:05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ModelInfoVo", description = "查询模型对象返回对象")
public class ModelInfoVo implements Serializable {

    private static final long serialVersionUID = -2434943659168309903L;

    private String id;

    @ApiModelProperty("model ID")
    private String modelId;

    @ApiModelProperty("model Key")
    private String modelKey;

    @ApiModelProperty("model 名称")
    private String modelName;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("modelXml内容")
    private String modelXml;

    @ApiModelProperty("modelXml内容")
    private String appSn;

    @ApiModelProperty("流程分类")
    private String categoryCode;

}
