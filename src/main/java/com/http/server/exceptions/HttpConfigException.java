package com.http.server.exceptions;

public class HttpConfigException extends Exception{

    public HttpConfigException() {
    }

    public HttpConfigException(String message) {
        super(message);
    }

    public HttpConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConfigException(Throwable cause) {
        super(cause);
    }
}
