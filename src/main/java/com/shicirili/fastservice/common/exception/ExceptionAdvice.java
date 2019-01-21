package com.shicirili.fastservice.common.exception;

import com.shicirili.fastservice.common.model.APIResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通过控制器增强，处理全局Exception
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public APIResponse handleException(Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return new APIResponse(exception.getCode(), exception.getMessage());
        }
        return new APIResponse(APIResponse.FAIL, e.getMessage());
    }
}
