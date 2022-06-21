package com.zukxu.common.log.model;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ${END}
 * </p>
 *
 * @author xupu
 * @since 2022/6/21 15:12:52
 */

/**
 * 操作记录表
 */
@ApiModel(value = "操作记录表")
@Data
@Accessors(chain = true)
@TableName(value = "sys_log_op_record")
public class SysLogOpRecord implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private String id;

    @TableField(value = "ip")
    @ApiModelProperty(value = "")
    private String ip;

    /**
     * 用户id
     */
    @TableField(value = "op_user_id")
    @ApiModelProperty(value = "用户id")
    private String opUserId;

    /**
     * 用户工号
     */
    @TableField(value = "op_user_no")
    @ApiModelProperty(value = "用户工号")
    private String opUserNo;

    /**
     * 用户name
     */
    @TableField(value = "op_user_name")
    @ApiModelProperty(value = "用户name")
    private String opUserName;

    /**
     * 模块名
     */
    @TableField(value = "`module`")
    @ApiModelProperty(value = "模块名")
    private String module;

    /**
     * 请求方式 GET POST
     */
    @TableField(value = "op_req_type")
    @ApiModelProperty(value = "请求方式 GET POST")
    private String opReqType;

    /**
     * 包含请求参数，类，方法
     */
    @TableField(value = "op_req_content")
    @ApiModelProperty(value = "包含请求参数，类，方法")
    private String opReqContent;

    @TableField(exist = false)
    private ReqContent reqContent;

    /**
     * 响应报文 包含code msg data
     */
    @TableField(value = "op_resp_content")
    @ApiModelProperty(value = "响应报文 包含code msg data")
    private String opRespContent;

    @TableField(exist = false)
    private RespContent respContent;

    /**
     * 响应异常
     */
    @TableField(value = "op_resp_ex")
    @ApiModelProperty(value = "响应异常")
    private String opRespEx;

    @TableField(value = "bgn_time")
    @ApiModelProperty(value = "")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bgnTime;

    @TableField(value = "`year`")
    @ApiModelProperty(value = "")
    private Integer year;

    @TableField(value = "`month`")
    @ApiModelProperty(value = "")
    private Integer month;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @TableField(value = "`day`")
    @ApiModelProperty(value = "")
    private Integer day;

    private static final long serialVersionUID = 1L;

    public void setOpReqContent(String opReqContent) {
        this.opReqContent = opReqContent;
    }

    public void setOpReqContent(ReqContent opReqContent) {
        this.opReqContent = JSON.toJSONString(opReqContent);
    }

    public void setOpRespContent(String opRespContent) {
        this.opRespContent = opRespContent;
    }

    public void setOpRespContent(RespContent opRespContent) {
        this.opRespContent = JSON.toJSONString(opRespContent);
    }

    public void setTitle(String title) {
        this.getReqContent().setTitle(title);
    }


    @Data
    @Accessors(chain = true)
    public class ReqContent implements Serializable {

        //@formatter:off
        String url;
        String title;
        String className;
        String methodName;
        String params;
        //@formatter:on

    }

    @Data
    @Accessors(chain = true)
    public class RespContent implements Serializable {

        //@formatter:off
        String code;
        String msg;
        Object data;
        //@formatter:on

    }

}