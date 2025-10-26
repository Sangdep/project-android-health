package com.SelfCare.SelftCare.Exception;

public enum ErrorCode {

    EMAIL_EXISTED(101,"email existed"),
    EMAIL_NOT_FOUND(102,"email not found"),
    UNAUTHENTICATED(999,"unauthenticated")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
