package com.trabalho.ufc.infrastructure.exception.advice;

import com.trabalho.ufc.infrastructure.exception.UfcException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UfcException.class)

    public ResponseEntity<Map<String, Object>> ufcExceptionHandler(Exception ex) {
        UfcException ufcException = (UfcException) ex;

        Map<String, Object> result = new HashMap<>();
        result.put("code", ufcException.getCode());
        result.put("description", ufcException.getDescription());
        result.put("status", ufcException.getStatus().value());
        if(!Objects.isNull(ufcException.getData())){
            result.put("data", ufcException.getData());
        }

        return new ResponseEntity(result, ufcException.getStatus());
    }
}
