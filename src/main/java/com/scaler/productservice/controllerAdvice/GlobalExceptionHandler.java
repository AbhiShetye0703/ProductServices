package com.scaler.productservice.controllerAdvice;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILURE");
        errorDto.setMessage("Something went wrong");

        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFoundExeption(ProductNotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILURE");
        errorDto.setMessage(exception.getMessage());

        return errorDto;
    }
}
