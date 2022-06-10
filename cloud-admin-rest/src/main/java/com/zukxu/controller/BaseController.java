package com.zukxu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zukxu.common.core.response.R;
import com.zukxu.common.core.vo.CheckExistVo;
import com.zukxu.common.security.service.LoginUser;
import com.zukxu.flowable.constants.FlowConstant;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * 基础接口抽象类
 * </p>
 *
 * @author xupu
 * @since 2022/6/9 17:42
 */
public abstract class BaseController<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String EXIST_MESSAGE = "不能重复！";

    /**
     * 获取登录用户
     *
     * @return LoginUser
     */
    protected LoginUser getLoginUser() {
        return null;
    }

    /**
     * 判断是否唯一,是否存在
     *
     * @param checkExistVo 参数
     *
     * @return boolean
     */
    protected R<Boolean> checkExist(IService<T> service, CheckExistVo checkExistVo) {
        R.builder().build();
        R<Boolean> r = R.ok(true);
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        String camelToUnderline = StringUtils.camelToUnderline(checkExistVo.getField());
        wrapper.eq(checkExistVo.getFieldValue(), camelToUnderline);
        wrapper.eq(FlowConstant.DEL_FLAG_, FlowConstant.NUM_1);
        long count = service.count(wrapper);
        if(StringUtils.isNotBlank(checkExistVo.getId())) {
            T entity = service.getById(checkExistVo.getId());
            try {
                Object fieldValue = FieldUtils.readField(entity, checkExistVo.getField(), true);
                String oldValue = (String) fieldValue;
                if(!oldValue.equals(checkExistVo.getFieldValue()) && count > 0) {
                    r.setMsg(checkExistVo.getFieldName() + EXIST_MESSAGE).setData(false);
                }
            } catch(IllegalAccessException e) {
                logger.error("没有相关的字段！字段为：{}", checkExistVo.getField());
            }
        } else {
            if(count > 0) {
                r.setMsg(checkExistVo.getFieldName() + EXIST_MESSAGE).setData(false);
            }
        }
        return r;
    }
}
