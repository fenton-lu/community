package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * 统一处理异常
 */

// ControllerAdvice注解只去扫描带有Controller注解的bean
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    // 用于修饰方法，该方法会在Controller出现异常后被调用，用于处理捕获到的异常
    @ExceptionHandler(Exception.class)
    public void handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("服务器发生异常："+e.getMessage());
        // 记录异常跟踪栈详细信息
        for(StackTraceElement element : e.getStackTrace()){
            logger.error(element.toString());
        }

        // 判断网页返回的是普通请求 还是 异步请求
        String xRequestedWith = request.getHeader("x-requested-with");
        // 异步请求，当值为XMLHttpRequest时为异步请求
        if("XMLHttpRequest".equals(xRequestedWith)){
            // 响应返回一个字符串
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1, "服务器异常！"));
        }else{
            response.sendRedirect(request.getContextPath()+"/error");
        }

    }

}
