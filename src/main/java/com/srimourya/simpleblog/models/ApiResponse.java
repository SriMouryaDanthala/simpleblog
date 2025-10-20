package com.srimourya.simpleblog.models;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private final HttpStatus status;
    private final String message;
    private final Object data;

    private ApiResponse(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse create(HttpStatus status, String message, Object data) {
        return new ApiResponse(status, message, data);
    }

    public static ApiResponse importAPIResponseFromService(ServiceResponse service) {
        return new ApiResponse(
                service.getHttpStatus(),
                service.getServiceMessage(),
                service.getServiceData()
        );
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
