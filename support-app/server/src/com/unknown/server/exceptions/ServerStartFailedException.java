package com.unknown.server.exceptions;

public class ServerStartFailedException extends RuntimeException {

    public ServerStartFailedException(Throwable cause) {
        super("Server start failed due to following error: ", cause);
    }
}
