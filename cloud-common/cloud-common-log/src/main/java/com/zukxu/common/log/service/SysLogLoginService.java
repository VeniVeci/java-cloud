package com.zukxu.common.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zukxu.common.core.base.QueryEntity;
import com.zukxu.common.log.model.SysLogLogin;

import java.util.List;

/**
 * <p>
 * ${END}
 * </p>
 *
 * @author xupu
 * @since 2022/6/21 10:47:04
 */
public interface SysLogLoginService extends IService<SysLogLogin> {

    /**
     * 查询列表
     *
     * @param query 参数
     *
     * @return list
     */
    List<SysLogLogin> selectList(QueryEntity query);

}



