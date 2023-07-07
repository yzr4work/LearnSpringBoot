package com.yzr.learnspringboot.handler;

import com.yzr.learnspringboot.pojo.view.resp.global.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @author yzr
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response paramNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        log.info("{}请求参数检验未通过", request.getRequestURL());
        Map<String, String> notValidMap = e.getBindingResult().getAllErrors().stream().map(objectError ->{
            FieldError fieldError = (FieldError) objectError;
            log.error(fieldError.getField() +"  " + fieldError.getDefaultMessage());
            return fieldError;
        } ).collect(Collectors.toMap(FieldError::getField, fieldError -> {
            String defaultMessage = fieldError.getDefaultMessage();
            return defaultMessage == null ? "" : defaultMessage;
        } ,(a,b) -> a + " " + b ));
        return Response.paramNotValidatedResp(notValidMap);
    }

}
