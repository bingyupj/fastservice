package com.shicirili.fastservice.controller.exception;


import com.shicirili.fastservice.common.exception.ExceptionAdvice;
import com.shicirili.fastservice.common.model.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * SpingMVC,如果出异常会转向全局的/error,
 * 这里对/error进行重写，统一异常处理(如果设置了@ControllerAdvice,此控制器失效)
 * @see     ExceptionAdvice#handleException(Exception)
 */
@Deprecated
@Controller
@EnableConfigurationProperties({ServerProperties.class})
public class ExceptionController implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;


    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public String error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        // getCause能获取整个堆栈信息
        String errMsg = ((Exception)request.getAttribute("javax.servlet.error.exception")).getMessage();
        return new APIResponse(status.value(),errMsg).toString();
    }


    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return "";
    }


}
