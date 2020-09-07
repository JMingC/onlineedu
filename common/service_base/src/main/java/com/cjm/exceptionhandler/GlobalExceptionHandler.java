package com.cjm.exceptionhandler;

import com.cjm.commonutils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CJiaM
 * @createTime 2020/9/7 15:07
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.ok().message("执行了全局异常处理");
    }
}
