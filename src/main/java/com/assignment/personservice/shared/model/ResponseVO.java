package com.assignment.personservice.shared.model;

public class ResponseVO<T> {
    private T data;

    public ResponseVO(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
