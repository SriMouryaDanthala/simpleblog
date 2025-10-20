package com.srimourya.simpleblog.models;

import org.springframework.http.HttpStatus;

public class ServiceResponse{

    private  Object serviceData;
    private  String serviceMessage;
    private  Exception serviceException;
    private      HttpStatus httpStatus;

    private ServiceResponse(Object serviceData, String serviceMessage, Exception serviceException, HttpStatus httpStatus){

        this.serviceData = serviceData;
        this.serviceMessage = serviceMessage;
        this.serviceException = serviceException;
        this.httpStatus = httpStatus;
    }

    public ServiceResponse() {}

    public  ServiceResponse create(Object serviceData, String serviceMessage, Exception serviceException, HttpStatus httpStatus){
        return new ServiceResponse(serviceData, serviceMessage, serviceException, httpStatus);
    }

    public ServiceResponse success(Object serviceData){
        return new ServiceResponse(serviceData, "success", null, HttpStatus.OK);
    }

    public ServiceResponse failure(String serviceMessage, Exception serviceException, HttpStatus httpStatus){
        return new ServiceResponse(null, serviceMessage, serviceException, httpStatus);
    }

    public String getServiceMessage() {
        return serviceMessage;
    }

    public Object getServiceData() {
        return serviceData;
    }

    public Exception getServiceException() {
        return serviceException;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
