package com.zukxu.common.log.controller;
import com.zukxu.common.log.model.SysLogOpRecord;
import com.zukxu.common.log.service.impl.SysLogOpRecordServiceImplImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* <p>
* (sys_log_op_record)表控制层
* </p>
* @author zukxu
*/
@RestController
@RequestMapping("/sys_log_op_record")
@AllArgsConstructor
public class SysLogOpRecordController {
/**
* 服务对象
*/

private final SysLogOpRecordServiceImpl sysLogOpRecordServiceImpl;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public SysLogOpRecord selectOne(Integer id) {
return sysLogOpRecordServiceImpl.selectByPrimaryKey(id);
}

}
