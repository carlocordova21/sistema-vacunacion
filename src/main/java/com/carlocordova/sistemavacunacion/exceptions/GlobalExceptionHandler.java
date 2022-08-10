package com.carlocordova.sistemavacunacion.exceptions;

import com.carlocordova.sistemavacunacion.dto.ErrorDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDescription> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDescription errorDetalles = new ErrorDescription(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VacunacionAppException.class)
    public ResponseEntity<ErrorDescription> handleVacunacionAppException(VacunacionAppException exception,WebRequest webRequest){
        ErrorDescription errorDetalles = new ErrorDescription(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDescription> handleGlobalException(Exception exception,WebRequest webRequest){
        ErrorDescription errorDetalles = new ErrorDescription(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();

            errores.put(nombreCampo, mensaje);
        });
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
