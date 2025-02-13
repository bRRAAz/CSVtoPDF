package com.api.generate_pdf.infra;

import com.api.generate_pdf.exceptions.FileRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileRequiredException.class)
    private ResponseEntity<String> FileRequiredHandler(FileRequiredException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }
}
