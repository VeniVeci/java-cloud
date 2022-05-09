package com.zukxu.common.core;

import com.zukxu.common.core.exception.BaseException;
import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022-05-09 17:27
 */
@RestController
@RequestMapping("/helloResult")
@RResponse
public class HelloResultController {

    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<String, Object>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    /** 测试重复包裹 */
    @GetMapping("result")
    public R<Map<String, Object>> helloResult() {
        return R.ok(INFO);
    }

    /** 测试String */
    @GetMapping("resultStr")
    public R<String> helloResultStr() {
        return R.ok("asdas");
    }

    /**
     * 测试数字
     *
     * @return
     */
    @GetMapping("resultInt")
    public R<Integer> helloResultInt() {
        return R.ok(2);
    }

    /**
     * 测试数字
     *
     * @return
     */
    @GetMapping("resultBool")
    public R<Boolean> helloResultBool() {
        return R.ok(false);
    }

    @GetMapping("helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new BaseException();
    }

}