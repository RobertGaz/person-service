package com.assignment.personservice.shared.model;

import java.util.List;

public class ErrorResponseVO {
    private List<ErrorVO> errors;

    public ErrorResponseVO() {
    }

    public ErrorResponseVO(List<ErrorVO> errors) {
        this.errors = errors;
    }

    public List<ErrorVO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorVO> errors) {
        this.errors = errors;
    }

    public void addError(ErrorVO errorVO) {
        errors.add(errorVO);
    }
}
