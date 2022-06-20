package com.zukxu.controller.flowable;

import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RResponse;
import com.zukxu.common.core.vo.CheckExistVo;
import com.zukxu.controller.BaseController;
import com.zukxu.flowable.model.FlowListenerParam;
import com.zukxu.flowable.service.IFlowListenerParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 流程监听器参数接口
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 15:04
 */
@RResponse
@RestController
@RequestMapping("/flowable/listenerParam")
public class FlowListenerParamResource extends BaseController {

    @Autowired
    private IFlowListenerParamService flowListenerParamService;

    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     *
     * @return
     */
    @PostMapping(value = "/checkEntityExist")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(flowListenerParamService, checkExistVo);
    }

    /**
     * 获取监听器参数列表
     *
     * @return
     */
    @GetMapping(value = "/getList/{listenerId}")
    public List<FlowListenerParam> getList(@PathVariable String listenerId) {
        return flowListenerParamService.getListByListenerId(listenerId);
    }

    /**
     * 保存监听参数
     *
     * @param flowListenerParam 参数
     *
     * @return
     */
    @PostMapping(value = "/saveOrUpdate")
    public Boolean saveOrUpdate(@RequestBody FlowListenerParam flowListenerParam) {
        return flowListenerParamService.saveOrUpdate(flowListenerParam);
    }

    /**
     * 删除
     *
     * @param id 参数
     *
     * @return
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public Boolean deleteById(@PathVariable String id) {
        return flowListenerParamService.removeById(id);
    }

}

