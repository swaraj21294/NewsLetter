package org.example.web.controller.exception;

public class TopicException extends RuntimeException {
    public TopicException(String message) {
        super(message);
    }
    public TopicException(String message, Throwable cause) {
        super(message, cause);
    }
}