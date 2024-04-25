package com.example.productservicessst.exceptionHandler;

import com.example.productservicessst.dtos.ExceptionDto;
import com.example.productservicessst.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(){

          ExceptionDto dto = new ExceptionDto();
          dto.setMessage("bad reques");
          dto.setResolution("pass correct id");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
          return response;
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity handleProductNotFound(){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("something went wrong");
        dto.setResolution("pass correct id");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        return response;
    }
}
