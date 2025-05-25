package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public interface ProductService {


    public GenericProductDto getProductById(Long id);

    public List<GenericProductDto> getAllProducts() ;
    public void deleteProductById(Long id);

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public void updateProductById(int id);

}
