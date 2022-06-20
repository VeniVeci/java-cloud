package com.zukxu.flowable.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zukxu.common.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * <p>
 * 流程分类实体类
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 15:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tbl_flow_category")
public class FlowCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 903848407599077900L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    //父id
    private String pid;

    //名称
    private String name;

    //编码
    private String code;

    /**
     * 是否前台显示 - 前台显示（1：显示，0：不显示）
     */
    private Integer frontShow;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名
     */
    @TableField(exist = false)
    private String cName;

    /**
     * 创建时间
     */
    @TableField(exist = false)
    private String createTimeStr;

    /**
     * 更新时间
     */
    @TableField(exist = false)
    private String updateTimeStr;
}
