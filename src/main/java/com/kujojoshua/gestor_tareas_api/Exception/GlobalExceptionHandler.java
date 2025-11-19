package com.kujojoshua.gestor_tareas_api.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFountException(ResourceNotFoundException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String, String> errores = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error)-> {
            //obtiene el nombre del campo que fallo
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();

            errores.put(nombreCampo, mensajeError);
        });

        return ResponseEntity.badRequest().body(errores);
    }
}
