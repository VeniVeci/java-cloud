package com.zukxu.flowable.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zukxu.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 扩展历史流程表
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tbl_flow_extend_hisprocinst")
public class ExtendHisprocinst extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 流程实例id
     */
    private String processInstanceId;

    private String processDefinitionId;//流程定义id

    /**
     * 模型key
     */
    private String modelKey;

    /**
     * 业务单据id
     */
    private String businessKey;

    /**
     * 流程状态
     */
    private String processStatus;

    /**
     * 流程表单名称
     */
    private String processName;

    /**
     * 流程发起人
     */
    private String currentUserCode;

    /**
     * 租户id(系统标识)
     */
    private String tenantId;

    public ExtendHisprocinst() {}

    public ExtendHisprocinst(String processInstanceId, String processStatus) {
        this.processInstanceId = processInstanceId;
        this.processStatus = processStatus;
    }

}
