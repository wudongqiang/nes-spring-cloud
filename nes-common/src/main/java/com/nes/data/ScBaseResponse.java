package com.nes.data;

import java.util.UUID;

/**
 * @author wdq
 * @date 2018-08-23-上午9:39
 */
public abstract class ScBaseResponse {
    private String traceId = "";
    private String code = "";
    private String message = "";

    public ScBaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.traceId = UUID.randomUUID().toString().replace("-", "");
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
