package com.unknown.supportapp.manager.ui.exceptions;

import com.unknown.supportapp.manager.ui.factory.WindowConfig;

public class UiLoadingException extends RuntimeException{

    public UiLoadingException(WindowConfig failedWindow, Throwable cause ) {
        super("Error during " + failedWindow + "load", cause);
    }
}
