package com.srimourya.simpleblog.dto;

import org.springframework.http.HttpStatus;

public class GenericResponse <T>{
    private T response;
    private HttpStatus status;
    private String message;

    public GenericResponse(T response) {
        this.setResponse(response);
        this.setStatus(HttpStatus.OK);
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GenericResponse<T> importSuccess(T data){
        this.setMessage("success");
        this.setResponse(data);
        this.setStatus(HttpStatus.OK);
        return this;
    }

}
