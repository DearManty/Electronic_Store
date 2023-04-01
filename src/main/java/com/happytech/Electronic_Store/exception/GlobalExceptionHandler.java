package com.happytech.Electronic_Store.exception;

import com.happytech.Electronic_Store.dto.ApiResposnse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResposnse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        logger.info("Exception Handler Invoked !!");
        ApiResposnse build = ApiResposnse.builder().messege(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();


        return new ResponseEntity<>(build,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        logger.info("Exception Handler Invoked !!");

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object> response=new HashMap<>();
        allErrors.stream().forEach(objectError ->{
            String message=objectError.getDefaultMessage();
            String field=((FieldError) objectError).getField();
            response.put(field, message);
        });

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResposnse> BadApiRequest(BadApiRequest ex){

        logger.info("Bad Api Request !!");
        ApiResposnse build = ApiResposnse.builder().messege(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();


        return new ResponseEntity<>(build,HttpStatus.BAD_REQUEST);
    }
}
