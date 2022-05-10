package com.zukxu.common.core;

import com.zukxu.common.core.exception.BaseException;
import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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
@Slf4j
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

    @PostMapping("helloFile")
    public File helloFile(@RequestBody Map<String, String> param) {
        String uploadDir = param.get("uploadDir");
        String fileName = param.get("fileName");
        File dir = new File(uploadDir);
        if(!dir.exists()) {
            dir.mkdirs();//创建目录
        }
        File f = new File(uploadDir, fileName);
        if(!f.exists()) {
            log.info("文件不存在!!!");
        }
        log.info("文件存储路径:::{}", f.getAbsolutePath());
        return f.isAbsolute() ? f : f.getAbsoluteFile();
    }

}