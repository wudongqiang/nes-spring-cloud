package com.socialcredits.gateservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: zhaopeng.chen
 * @date: 17-12-20 上午11:40
 */
@RestController
@RequestMapping("/customErr")
public class CustErrorController {
    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> map = (Map<String, Object>) request.getAttribute("errMap");
        Integer httpStatus = (Integer) request.getAttribute("httpStatus");
        return new ResponseEntity(map, HttpStatus.valueOf(httpStatus));
    }
}
