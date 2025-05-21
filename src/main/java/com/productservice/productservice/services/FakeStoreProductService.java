package com.productservice.productservice.services;


import org.springframework.stereotype.Service;

@Service
public class  FakeStoreProductService implements ProductService{


    @Override
    public String getProductById(Long id) {
        return "Product with id " + id + " found";
    }

    @Override
    public void getaAllProducts() {

    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProductById(int id) {

    }
}
