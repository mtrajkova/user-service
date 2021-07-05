package com.bachelor.microservice2.model;


public class HttpResponse {
    private String errorMessage;
    private String successMessage;
    private int httpStatus;

    public HttpResponse(String errorMessage, String successMessage, int httpStatus) {
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public int getResponseCode() {
        return httpStatus;
    }
}
