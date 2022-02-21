package com.ksinfo.tomodomo.constant;

public enum Code {
    SEARCH(37),
    DELETE(3924);

    private final int code;

    Code(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}