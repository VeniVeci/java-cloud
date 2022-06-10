package com.zukxu.flowable.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 节点高亮信息
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 10:07
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "HighLightedNodeVo", description = "高亮节点信息")
public class HighLightedNodeVo implements Serializable {

    public HighLightedNodeVo(List<String> highLightedFlows, List<String> activeActivityIds) {
        this.highLightedFlows = highLightedFlows;
        this.activeActivityIds = activeActivityIds;
    }

    @ApiModelProperty("高亮线id集合")
    private List<String> highLightedFlows;

    @ApiModelProperty("高亮节点id集合")
    private List<String> activeActivityIds;

    @ApiModelProperty("model的xml文件")
    private String modelXml;

    @ApiModelProperty("model名称")
    private String modelName;

}
