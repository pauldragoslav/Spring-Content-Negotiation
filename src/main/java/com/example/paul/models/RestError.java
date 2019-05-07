package com.example.paul.models;

public class RestError {
    private String code;

    protected RestError() {}
    public RestError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RestError{" +
                "code='" + code + '\'' +
                '}';
    }
}
