package com.insureai.insureai_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        // Check what kind of error
        String msg = ex.getMessage();

        if (msg != null && msg.contains("PENDING")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error);
        }

        if (msg != null && msg.contains("REJECTED")) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(error);
        }

        if (msg != null && (msg.contains("not found") ||
                msg.contains("Invalid password") ||
                msg.contains("already exists"))) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}