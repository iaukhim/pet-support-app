package com.unknown.supportapp.server.exceptions;

public class ServerFatalError extends RuntimeException {

    public ServerFatalError (Throwable cause) {
        super("Server can not process requests due to the following error: ", cause);
    }

}
