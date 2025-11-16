package org.example.web.controller.exception;

public class TopicAlreadyExistsException extends RuntimeException {
    public TopicAlreadyExistsException(String message) {
        super(message);
    }
    public TopicAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}