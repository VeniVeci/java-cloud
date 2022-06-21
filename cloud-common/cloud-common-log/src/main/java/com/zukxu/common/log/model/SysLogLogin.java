package com.zukxu.common.log.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ${END}
 * </p>
 *
 * @author xupu
 * @since 2022/6/21 10:54:49
 */

/**
 * 登录日志表
 */
@ApiModel(value = "登录日志表")
@Data
@AllArgsConstructor
@TableName(value = "sys_log_login")
public class SysLogLogin implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private String id;

    /**
     * 操作id
     */
    @TableField(value = "operation_id")
    @ApiModelProperty(value = "操作id")
    private String operationId;

    /**
     * 操作账号
     */
    @TableField(value = "operation_name")
    @ApiModelProperty(value = "操作账号")
    private String operationName;

    @TableField(value = "module")
    @ApiModelProperty(value = "模块")
    private String module;

    /**
     * 访问ip
     */
    @TableField(value = "ip")
    @ApiModelProperty(value = "访问ip")
    private String ip;

    /**
     * 登录/注销 0/1
     */
    @TableField(value = "is_login")
    @ApiModelProperty(value = "登录/注销 0/1")
    private Boolean isLogin;

    /**
     * 操作时间
     */
    @TableField(value = "operation_time")
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operationTime;

    private static final long serialVersionUID = 1L;

}