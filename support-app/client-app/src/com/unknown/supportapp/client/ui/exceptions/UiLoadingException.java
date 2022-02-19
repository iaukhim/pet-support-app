package com.unknown.supportapp.client.ui.exceptions;

import com.unknown.supportapp.client.ui.factory.WindowConfig;

public class UiLoadingException extends RuntimeException{

    public UiLoadingException(WindowConfig failedWindow, Throwable cause ) {
        super("Error during " + failedWindow + "load", cause);
    }
}
