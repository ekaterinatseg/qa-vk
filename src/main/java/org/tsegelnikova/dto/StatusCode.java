package org.tsegelnikova.dto;

public enum StatusCode {
    SUCCESS(200),
    CREATED(201),
    NOT_FOUND(404);

    public final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public static StatusCode valueOfCode(int code) {
        for (StatusCode e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }
}
