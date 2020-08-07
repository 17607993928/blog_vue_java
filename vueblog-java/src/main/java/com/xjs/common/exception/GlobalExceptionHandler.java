package com.xjs.common.exception;

import com.xjs.common.lang.AjaxCode;
import com.xjs.common.lang.AjaxMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 xjs
 * @类描述 全局异常处理
 * @创建时间 2020/8/5 0005
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BasicErrorController {

    public GlobalExceptionHandler() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    /**
     * 捕获shiro中filter异常
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = HttpStatus.UNAUTHORIZED;//强行返回状态401
        // 获取错误信息
        String message = body.get("message").toString();
        System.out.println(message);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "401");
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }

    /**
     * 普通异常400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public AjaxMessage handler(RuntimeException e) {
        log.error("运行时异常----------" + e);
        e.printStackTrace();
        return AjaxMessage.error(e.getMessage(),"400");
    }

    /**
     * shiro异常401
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public AjaxMessage handler(ShiroException e) {
        log.error("shiro权限异常----------" + e);
        e.printStackTrace();
        return AjaxMessage.error(e.getMessage(),"401");
    }

    /**
     * shiro异常401
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public AjaxMessage handler(UnauthenticatedException e) {
        log.error("shiro权限异常UnauthenticatedException----------" + e);
        e.printStackTrace();
        return AjaxMessage.error("您没有权限访问哦！！","401");
    }


    /**
     * 数据校验异常
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxMessage handler(MethodArgumentNotValidException e) {
        log.error("数据校验异常----------" + e);
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        return AjaxMessage.error(objectError.getDefaultMessage(),AjaxCode.VALID);
    }

    /**
     * 断言异常9999
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public AjaxMessage handler(IllegalArgumentException e){
        log.error("断言异常----------" + e);
        e.printStackTrace();
        return AjaxMessage.error(e.getMessage(), AjaxCode.ASSERT);
    }

}
