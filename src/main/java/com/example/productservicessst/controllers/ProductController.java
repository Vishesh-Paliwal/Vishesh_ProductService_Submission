package com.example.productservicessst.controllers;

import com.example.productservicessst.dtos.ExceptionDto;
import com.example.productservicessst.dtos.FakeStoreProductDto;
import com.example.productservicessst.models.Category;
import com.example.productservicessst.models.product;
import com.example.productservicessst.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public static String sayHello(){
        return  "Hello World";
    }


    @GetMapping("/products/{id}")
    public product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products")
    public List<product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/category")
    public List<String> getAllCategories(){
        return productService.getCategory();
    }

    @DeleteMapping("/products/{id}")
    public void deletebyId(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/update")
        public void updateProduct(@RequestBody product p){
            productService.updateProduct(p);
        }

        @PostMapping("/products")
        public FakeStoreProductDto createProduct(@RequestBody product p){
            return productService.createProduct(p);
        }

    @GetMapping("/products/category/{categoryN}")
    public List<product> getInCategory(@PathVariable("categoryN") String categoryName) {
        return productService.getProductByCategory(categoryName);
    }

    @GetMapping("/sort")
    public List<product> getSorted(){
        return productService.getSortedList();
    }
}
