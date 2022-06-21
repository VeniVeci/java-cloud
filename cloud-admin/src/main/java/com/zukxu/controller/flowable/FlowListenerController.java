package com.zukxu.controller.flowable;


import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RResponse;
import com.zukxu.common.core.vo.CheckExistVo;
import com.zukxu.controller.BaseController;
import com.zukxu.flowable.model.FlowListener;
import com.zukxu.flowable.service.IFlowListenerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 监听器相关接口
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:34
 */
@RResponse
@RestController
@RequestMapping("/flowable/listener")
@AllArgsConstructor
public class FlowListenerController extends BaseController {

    //@formatter:off
    final private IFlowListenerService flowListenerService;
    //@formatter:on

    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     *
     * @return true/false
     */
    @PostMapping(value = "/checkEntityExist")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(flowListenerService, checkExistVo);
    }

    /**
     * 获取列表
     *
     * @return FlowListener
     */
    @GetMapping(value = "/getList")
    public List<FlowListener> getList(@RequestBody FlowListener flowListener) {
        return flowListenerService.getList(flowListener);
    }

    /**
     * 保存监听
     *
     * @param flowListener 参数
     *
     * @return true/false
     */
    @PostMapping(value = "/saveOrUpdate")
    public Boolean saveOrUpdate(@RequestBody FlowListener flowListener) {
        return flowListenerService.saveOrUpdate(flowListener);
    }

    /**
     * 删除
     *
     * @param id 参数
     *
     * @return true/false
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public Boolean deleteById(@PathVariable String id) {
        return flowListenerService.deleteById(id);
    }

    /**
     * 通过id获取监听器 包含参数
     *
     * @param id 参数
     *
     * @return FlowListener
     */
    @GetMapping(value = "/getById/{id}")
    public FlowListener getById(@PathVariable String id) {
        return flowListenerService.getFlowListenerById(id);
    }

}

