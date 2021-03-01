package com.example.demo.handler;

import com.example.demo.exception.ValidationException;
import com.example.demo.response.ExceptionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER_HANDLER = LogManager.getLogger(DemoExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ExceptionResponse handleValidationException(ValidationException ex, HttpServletRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<String> messages = new ArrayList<>();
        for (FieldError e : errors){
            messages.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
        }
        return new ExceptionResponse(300, "ValidationException", messages,
            request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionResponse handleAllException(Exception ex, HttpServletRequest request) {

        String exceptionName = ex.getClass().getSimpleName();
        List<String> details = new ArrayList<>();
        LOGGER_HANDLER.info("message is : " + ex.getLocalizedMessage());
        details.add(ex.getLocalizedMessage());
        return new ExceptionResponse(400, exceptionName, details,
            request.getRequestURI());
    }
}
