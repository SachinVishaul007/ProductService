package com.productservice.productservice.controllers;


import com.productservice.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product with id " + id + " found";

    }

    @GetMapping
    public void getaAllProducts() {

    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {

    }

    public void createProduct() {

    }

    public void updateProductById(int id) {

    }


}
