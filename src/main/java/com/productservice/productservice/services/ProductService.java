package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public interface ProductService {


    public GenericProductDto getProductById(Long id) throws ProductNotFoundException;

    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException;
    public GenericProductDto deleteProductById(Long id) throws ProductNotFoundException;

    public GenericProductDto createProduct(GenericProductDto genericProductDto) ;

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws ProductNotFoundException;

}
