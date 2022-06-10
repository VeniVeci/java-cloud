package com.zukxu.flowable.model.enums;


import com.zukxu.common.core.response.R;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 *  流程表单状态
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 15:12
 */
@Getter
public enum FlowStatusEnums {

    /**
     * 流程表单状态
     */
    CG(1, "草稿"), DFB(2, "待发布"), YFB(3, "已发布"), TY(4, "停用");

    //@formatter:off
    private Integer status;
    private String msg;
    //@formatter:on

    FlowStatusEnums(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 根据status查询msg
     *
     * @param status
     *
     * @return
     */
    public static String getName(Integer status) {
        if(null == status) {
            return null;
        }
        for(FlowStatusEnums item : FlowStatusEnums.values()) {
            if(status == item.getStatus().intValue()) {
                return item.getMsg();
            }
        }
        return null;
    }

    /**
     * 根据status 查询 枚举
     *
     * @param status
     *
     * @return
     */
    public static FlowStatusEnums getEnum(Integer status) {
        if(null == status) {
            return null;
        }
        for(FlowStatusEnums item : FlowStatusEnums.values()) {
            if(status == item.getStatus().intValue()) {
                return item;
            }
        }
        return null;
    }

    /**
     * 检查是否符合激活状态
     *
     * @param formStatus   自定义表单状态
     * @param modelStatus  流程状态
     * @param extendStatus 流程拓展信息状态
     *
     * @return
     */
    public static R<String> checkActive(Integer formStatus, Integer modelStatus, Integer extendStatus) {
        if(null == formStatus || null == modelStatus || null == extendStatus) {
            return R.fail("表单状态异常");
        }
        if(!formStatus.equals(DFB.status) && !formStatus.equals(YFB.status)) {
            return R.fail("未定义或发布表单");
        }
        return getStringR(modelStatus, extendStatus);
    }

    private static R<String> getStringR(Integer modelStatus, Integer extendStatus) {
        if(!modelStatus.equals(DFB.status) && !modelStatus.equals(YFB.status)) {
            return R.fail("未定义或发布流程模型信息");
        }
        if(!extendStatus.equals(DFB.status) && !extendStatus.equals(YFB.status)) {
            return R.fail("未定义或发布流程配置信息");
        }
        return R.ok();
    }

    /**
     * @param modelStatus  流程状态
     * @param extendStatus 流程拓展信息状态
     *
     * @return
     */
    public static R<String> checkActive(Integer modelStatus, Integer extendStatus) {
        if(null == modelStatus || null == extendStatus) {
            return R.fail("流程模型状态异常");
        }
        return getStringR(modelStatus, extendStatus);
    }

    public static FlowStatusEnums getMinStatus(Integer formStatus, Integer modelStatus, Integer extendStatus) {
        List<Integer> list = new ArrayList<>();
        list.add(formStatus);
        list.add(modelStatus);
        list.add(extendStatus);
        Optional<Integer> min = list.stream().min(Integer::compare);
        return FlowStatusEnums.getEnum(min.get());
    }

    public static FlowStatusEnums getMinStatus(Integer modelStatus, Integer extendStatus) {
        return getMinStatus(null, modelStatus, extendStatus);
    }
}
