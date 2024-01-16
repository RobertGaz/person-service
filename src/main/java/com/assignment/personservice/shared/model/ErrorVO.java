package com.assignment.personservice.shared.model;

import java.util.UUID;

public class ErrorVO {

    private UUID id;
    private String source;
    private String code;
    private String detail;

    public ErrorVO(String source, String code, String detail) {
        this.id = UUID.randomUUID();
        this.source = source;
        this.code = code;
        this.detail = detail;
    }

    public ErrorVO(UUID id, String source, String code, String detail) {
        this.id = id;
        this.source = source;
        this.code = code;
        this.detail = detail;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}