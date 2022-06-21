package com.zukxu.common.log.controller;

import com.zukxu.common.core.base.QueryEntity;
import com.zukxu.common.core.response.RResponse;
import com.zukxu.common.log.model.SysLogLogin;
import com.zukxu.common.log.service.impl.SysLogLoginServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * (sys_log_login)表控制层
 * </p>
 *
 * @author zukxu
 */
@RResponse
@RestController
@RequestMapping("/log/login")
@AllArgsConstructor
public class SysLogLoginController {

    /**
     * 服务对象
     */

    private final SysLogLoginServiceImpl sysLogLoginServiceImpl;

    /**
     * 通过查询条件查询列表
     *
     * @param query 查询实体
     *
     * @return 数据列表
     */
    @GetMapping("/list")
    public List<SysLogLogin> list(QueryEntity query) {
        return sysLogLoginServiceImpl.selectList(query);
    }

}
