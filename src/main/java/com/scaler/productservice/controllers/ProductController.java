package com.scaler.productservice.controllers;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.ErrorDto;
import com.scaler.productservice.dtos.ProductRequestDto;
import com.scaler.productservice.dtos.ProductResponseDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public  ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        List<ProductResponseDto> responseDtos = new ArrayList<>();

        for (Product product: productList){
            responseDtos.add(ProductResponseDto.from(product));
        }

        return  responseDtos;
    }

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto){
        Product product = productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getImageUrl(),
                requestDto.getCategoryName()
        );

        return ProductResponseDto.from(product);
    }

    public void deleteProduct(){

    }

    public  void updateProduct(){

    }

    // only invoked in Product Controller Class
    // if there is any other controller that throws NPE, this won't be called
//    @ExceptionHandler(NullPointerException.class)
//    public ErrorDto nullPointerExceptionHandler(){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setStatus("FAILURE");
//        errorDto.setMessage("Something went wrong");
//
//        return errorDto;
//    }

}
