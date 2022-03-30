package com.zukxu.common.security.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 认证授权相关工具类
 */
@Slf4j
@UtilityClass
public class AuthUtils {

    private final String BASIC_ = "basic";

    /**
     * 提取并解密请求头参数
     *
     * @param header 请求头
     *
     * @return []
     */
    @SneakyThrows
    public String[] extractAndDecodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded = new byte[0];
        try {
            Base64.decode(base64Token);
        } catch(IllegalArgumentException e) {
            throw new RuntimeException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, StandardCharsets.UTF_8);
        int i = token.indexOf(":");
        if(i == -1) {
            throw new RuntimeException("Invalid basic authentication token");
        }
        return new String[]{ token.substring(0, i), token.substring(i + 1) };
    }

    @SneakyThrows
    public String[] extractAndDecodeHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StrUtil.isEmpty(header) || !StrUtil.startWith(header, BASIC_)) {
            throw new RuntimeException("请求头中client信息为空");
        }
        return extractAndDecodeHeader(header);
    }

}
