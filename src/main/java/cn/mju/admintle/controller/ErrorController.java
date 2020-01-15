package cn.mju.admintle.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;


//@ControllerAdvice
public class ErrorController {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public String handlerException(Exception e, HandlerMethod handlerMethod){
        //错误信息
       logger.info("出错原因"+e.getMessage());
       logger.info("出错类名"+handlerMethod.getBean().getClass());
       logger.info("出错方法名："+handlerMethod.getMethod().getName());

        return "error";
    }
}
