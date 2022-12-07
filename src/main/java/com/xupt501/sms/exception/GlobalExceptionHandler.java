package com.xupt501.sms.exception;

import com.xupt501.sms.common.MessageConstant;
import com.xupt501.sms.common.Result;
import com.xupt501.sms.common.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获Exception类型的异常，并进行友好错误提示
     * @param exception
     * @return : com.xupt501.sms.common.Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception exception){
        exception.printStackTrace();
        return new Result(false, StatusCode.ERROR, MessageConstant.SYSTEM_BUSY);
    }
}
