package com.productservice.productservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public interface ProductService {


    public String getProductById(Long id);

    public void getaAllProducts() ;
    public void deleteProductById(Long id);

    public void createProduct();

    public void updateProductById(int id);

}
