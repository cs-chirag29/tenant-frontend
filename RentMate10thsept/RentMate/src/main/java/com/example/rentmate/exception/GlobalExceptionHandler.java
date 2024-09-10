package com.example.rentmate.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TenantAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTenantAlreadyExistsException(TenantAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.CONFLICT.value()); // HTTP 409 Conflict
        errorResponse.setTimestamp(System.currentTimeMillis());
        // No stack trace included
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("An unexpected error occurred."); // Generic error message
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setTimestamp(System.currentTimeMillis());
        // Optionally log the exception details if needed
        // log.error("Unexpected error occurred", ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Malformed JSON request: " + ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        String userFriendlyMessage = "A data integrity violation occurred. Please check your input and try again.";
//        String detailMessage = extractViolationDetails(ex.getMostSpecificCause().getMessage());
//
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setMessage(userFriendlyMessage);
//        errorResponse.setDetail(detailMessage); // Include specific detail about the violation
//        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value()); // HTTP 400 Bad Request
//        errorResponse.setTimestamp(System.currentTimeMillis());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(propertyPath, errorMessage);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        // Extract and map the error details
        String errorMessage = extractViolationDetails(ex.getMostSpecificCause().getMessage());

        if (errorMessage.contains("email")) {
            errors.put("email", "Email address already exists");
        } else if (errorMessage.contains("username")) {
            errors.put("username", "Username already exists");
        } else {
            errors.put("error", "A data integrity violation occurred. Please check your input and try again.");
        }

        // Return the custom error response
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private String extractViolationDetails(String message) {
        // Example regex pattern to extract key-value pair details from the exception message
        String pattern = "Key \\(([^)]+)\\)=\\(([^)]+)\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(message);

        if (m.find()) {
            String field = m.group(1); // Field name
            String value = m.group(2); // Violation value
            return String.format("Duplicate value found: %s=%s", field, value);
        }
        return "Could not determine the exact data that caused the violation.";
    }
}


//    private String extractViolationDetails(String message) {
//        // Example regex pattern to extract key-value pair details from the exception message
//        String pattern = "Key \\(([^)]+)\\)=\\(([^)]+)\\)";
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(message);
//
//        if (m.find()) {
//            String field = m.group(1); // Field name
//            String value = m.group(2); // Violation value
//            return String.format("Duplicate value found: %s=%s", field, value);
//        }
//        return "Could not determine the exact data that caused the violation.";
//    }