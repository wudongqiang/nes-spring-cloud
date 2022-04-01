package com.social.feigndata.api;

import com.nes.data.ScBaseDataType;
import com.nes.data.ScResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class MyExceptionHandler {

    /**
     * 异常处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ScResponse<ScBaseDataType<String>> errorHandler(Exception ex) {
        return new ScResponse<>(ScBaseDataType.of(ex.getMessage()));
    }

}
