package com.zukxu.controller.flowable;

import com.zukxu.common.core.response.RResponse;
import com.zukxu.common.core.vo.CheckExistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  流程分类接口
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 15:13
 */
@RResponse
@RestController
@RequestMapping("/flow/base/category")
public class FlowCategoryController extends BaseResource<FLowCategory> {
    private static final String MODULE_SN = "Category:";
    @Autowired
    private ICategoryService categoryService;
    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */
    @PostMapping(value = "/checkEntityExist", produces = "application/json")
    protected ReturnVo<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(categoryService, checkExistVo);
    }
    /**
     * 获取分类全部数据
     *
     * @param category 参数
     * @return
     */
    @PostMapping(value = "/getCategories", produces = "application/json")
    public ReturnVo<List> getCategories(@RequestBody Category category) {
        ReturnVo<List> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        List<Category> categories = categoryService.getCategories(category);
        returnVo.setData(categories);
        return returnVo;
    }

    /**
     * 添加或者修改
     *
     * @param category 参数
     * @return
     */
    @PostMapping(value = "/saveOrUpdate", produces = "application/json")
    public ReturnVo<String> saveOrUpdate(@RequestBody Category category) {
        ReturnVo<String> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        categoryService.saveOrUpdate(category, this.getLoginUser());
        return returnVo;
    }

    /**
     * 删除应用
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete", produces = "application/json")
    public ReturnVo<String> delete(@RequestBody List<String> ids) {
        ReturnVo<String> returnVo = categoryService.deleteByIds(ids);
        return returnVo;
    }

    /**
     * 查询应用
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get", produces = "application/json")
    public ReturnVo<Category> get(@RequestBody String id) {
        ReturnVo<Category> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        Category flowCategory = categoryService.getById(id);
        returnVo.setData(flowCategory);
        return returnVo;
    }
}
