package nl.roymprog.cardsscore.controllers;

import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    private class ApiError {
        public String getMessage() {
            return message;
        }

        private final String message;

        private ApiError(String message) {
            this.message = message;
        }
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class, IllegalAccessException.class, RuntimeException.class})
    public final ResponseEntity<ApiError> handleException(Exception e, WebRequest req) {
        if (e instanceof IllegalArgumentException ) {
            return new ResponseEntity<ApiError>(new ApiError(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
            String message = String.format("Invalid property, %s %s",
                    manve.getBindingResult().getFieldError().getField(),
                    manve.getBindingResult().getFieldError().getDefaultMessage());
            return new ResponseEntity<ApiError>(new ApiError(message), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else if (e instanceof IllegalAccessException) {
            return new ResponseEntity<ApiError>(new ApiError(e.getMessage()), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }
        else {
            e.printStackTrace();
            log.severe(e.getMessage());
            return new ResponseEntity<>(new ApiError("Something went wrong"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
