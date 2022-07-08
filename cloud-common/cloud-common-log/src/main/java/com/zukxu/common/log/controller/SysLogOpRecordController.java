package com.zukxu.common.log.controller;

import com.zukxu.common.log.model.SysLogOpRecord;
import com.zukxu.common.log.service.impl.SysLogOpRecordServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * (sys_log_op_record)表控制层
 * </p>
 *
 * @author zukxu
 */
@RestController
@RequestMapping("/sys_log_op_record")
@AllArgsConstructor
public class SysLogOpRecordController {

    private final SysLogOpRecordServiceImpl sysLogOpRecordServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     *
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysLogOpRecord selectOne(Integer id) {
        return sysLogOpRecordServiceImpl.getById(id);
    }

}
