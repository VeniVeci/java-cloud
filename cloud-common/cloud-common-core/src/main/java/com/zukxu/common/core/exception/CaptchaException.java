package com.zukxu.common.core.exception;

import com.zukxu.common.core.response.RStatus;

/**
 * <p>
 * 验证码异常
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 15:50
 */
public class CaptchaException extends BaseException {
    public CaptchaException() {
        super(RStatus.CAPTCHA_ERROR_);
    }
}
