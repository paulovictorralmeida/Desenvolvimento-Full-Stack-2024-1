package com.trabalho.ufc.infrastructure.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class UfcException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String code;
    private final HttpStatus status;
    private final String description;
    private Object data;


    public UfcException(String code, HttpStatus status, String description){
        this.code = code;
        this.status = status;
        this.description = description;
    }

    public UfcException(String code, HttpStatus status, String description, Object data){
        this.code = code;
        this.status = status;
        this.description = description;
        this.data = data;
    }

    public static UfcException badRequest(String code, String description){
        return new UfcException(code, HttpStatus.BAD_REQUEST, description);
    }

    public static UfcException unauthorized(String code, String description){
        return new UfcException(code, HttpStatus.UNAUTHORIZED, description);
    }

    public static UfcException forbidden(String code, String description){
        return new UfcException(code, HttpStatus.FORBIDDEN, description);
    }

    public static UfcException notFound(String code, String description){
        return new UfcException(code, HttpStatus.NOT_FOUND, description);
    }

    public static UfcException conflict(String code, String description){
        return new UfcException(code, HttpStatus.CONFLICT, description);
    }

    public static UfcException preconditionFailed(String code, String description){
        return new UfcException(code, HttpStatus.PRECONDITION_FAILED, description);
    }

    public static UfcException preconditionFailed(String code, String description, Object data){
        return new UfcException(code, HttpStatus.PRECONDITION_FAILED, description, data);
    }

}
