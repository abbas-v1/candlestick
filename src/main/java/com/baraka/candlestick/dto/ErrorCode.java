package com.baraka.candlestick.dto;

/**
 * API response error codes
 *
 * @author abbas
 */
public enum ErrorCode {

    API_NOT_FOUND(404, "API not found"),
    INVALID_REQUEST_PARAM(400, "%s"),
    INVALID_REQUEST_METHOD(400, "%s"),
    INTERNAL_SERVER_ERROR(500, "Unable to complete action"),
    UNKNOWN_ERROR(500, "Uknown exception");

    private final int httpStatusCode;
    private final String message;

    ErrorCode(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public int httpStatusCode() {
        return this.httpStatusCode;
    }

    public String message() {
        return this.message;
    }

}
