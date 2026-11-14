package com.example.first_project.controllerAdvice;

import com.example.first_project.dtos.ExceptionDto;
import com.example.first_project.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("ArithMatic Exception occur");
        exceptionDto.setSolution("Solution. I don't know");


        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product Not Found");
        exceptionDto.setSolution("i don't know");


        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
    }
}
