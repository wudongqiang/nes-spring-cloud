package com.nes.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Optional;

public class ScResponse<T> extends ScBaseResponse {

    private T data;

    public ScResponse(T data, ScSysExceptionStatus status) {
        super(status.getCode(), status.getMessage());
        this.data = data;
    }

    public ScResponse(ScSysExceptionStatus status) {
        super(status.getCode(), status.getMessage());
    }

    public ScResponse(T data) {
        this(data, ScSysExceptionStatus.SUCCESS);
    }

    public ScResponse() {
        this(null, ScSysExceptionStatus.SUCCESS);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    public Optional<T> getOptionalData() {
        return Optional.ofNullable(data);
    }
}
