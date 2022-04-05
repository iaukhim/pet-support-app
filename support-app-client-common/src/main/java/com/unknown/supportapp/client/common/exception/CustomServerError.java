package com.unknown.supportapp.client.common.exception;

public class CustomServerError extends Exception {

    public CustomServerError() {
    }

    private String errorTitle;

    private String errorDescription;

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
