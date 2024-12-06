package com.sportify.backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Standardized error response structure.
     */
    private static class ApiError {
        private final int status;
        private final String message;
        private final Instant timestamp;
        private final List<String> errors;

        public ApiError(int status, String message, List<String> errors) {
            this.status = status;
            this.message = message;
            this.errors = errors;
            this.timestamp = Instant.now();
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public List<String> getErrors() {
            return errors;
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        LOGGER.warn("Resource not found", ex);
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Resource not found", List.of("The requested resource could not be found"));
        return buildResponseEntity(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler(AtletaNotFoundException.class)
    public ResponseEntity<Object> handleAtletaNotFoundException(AtletaNotFoundException ex) {
        LOGGER.warn("Resource not found", ex);
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Resource not found", List.of("The requested resource could not be found"));
        return buildResponseEntity(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        LOGGER.warn("Client error occurred", ex);
        ApiError error = new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid request",
            List.of("The request could not be processed")
        );
        return buildResponseEntity(HttpStatus.BAD_REQUEST, error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.warn("Validation error", ex);
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                List.of("The provided input is invalid")
        );
        return buildResponseEntity(HttpStatus.BAD_REQUEST, error);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.warn("No handler found", ex);
        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                List.of("The requested endpoint does not exist")
        );
        return buildResponseEntity(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex) {
        LOGGER.error("Internal server error", ex);
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                List.of("An unexpected error occurred")
        );
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, error);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, ApiError error) {
        return new ResponseEntity<>(error, status);
    }

    private String formatValidationError(FieldError fieldError) {
        return "Invalid input";
    }
}
