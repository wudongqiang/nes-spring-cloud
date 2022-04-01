package com.nes.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * 分页对象返回响应
 */
public class ScPageResponse<T> extends ScBaseResponse {

    private ScPageData<T> data;

    public ScPageResponse() {
        this(ScSysExceptionStatus.SUCCESS);
    }

    public ScPageResponse(ScSysExceptionStatus status) {
        super(status.getCode(), status.getMessage());
    }

    public ScPageResponse(Page<T> data, ScSysExceptionStatus status) {
        super(status.getCode(), status.getMessage());
        this.data = new ScPageData<>(data);
    }

    public ScPageResponse(ScPageData<T> data, ScSysExceptionStatus status) {
        super(status.getCode(), status.getMessage());
        this.data = data;
    }

    public ScPageResponse(Page<T> data) {
        this(data, ScSysExceptionStatus.SUCCESS);
    }

    public ScPageResponse(ScPageData<T> data) {
        this(data, ScSysExceptionStatus.SUCCESS);
    }

    public ScPageData<T> getData() {
        return data;
    }

    public void setData(ScPageData<T> data) {
        this.data = data;
    }

    @JsonIgnore
    public Optional<ScPageData<T>> getOptionalData() {
        return Optional.ofNullable(data);
    }
}
