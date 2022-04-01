package com.nes.data;

/**
 * 用于包装基本数据类型
 *
 * @author wdq
 * @date 2018-08-23-上午10:23
 */
public class ScBaseDataType<T> {

    private T result;

    public ScBaseDataType() {
    }

    private ScBaseDataType(T result) {
        this.result = result;
    }

    public static <T> ScBaseDataType<T> of(T value) {
        return new ScBaseDataType<>(value);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}