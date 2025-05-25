package com.productservice.productservice.services;


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class  FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTempateBuilder;

    private String specificProductURL = "https://fakestoreapi.com/products/{id}";
    private String genericProductURL = "https://fakestoreapi.com/products";

    FakeStoreProductService(RestTemplateBuilder restTempateBuilder) {
        this.restTempateBuilder = restTempateBuilder;
    }

    private static GenericProductDto toGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {

        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
//        String url  = baseProductURL + "/" + id;
        RestTemplate template = restTempateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = template.getForEntity(specificProductURL, FakeStoreProductDto.class,id);

        return toGenericProductDto(responseEntity.getBody());


    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate template = restTempateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = template.getForEntity(genericProductURL, FakeStoreProductDto[].class);
        List<GenericProductDto> result = new ArrayList<>();

        if (responseEntity.getBody() != null) {
            for (FakeStoreProductDto fakeStoreProductDto : responseEntity.getBody()) {
                result.add(toGenericProductDto(fakeStoreProductDto));
            }
        }

        return result;

    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
      RestTemplate template = restTempateBuilder.build();
      ResponseEntity<FakeStoreProductDto> responseEntity = template.postForEntity(genericProductURL, genericProductDto, FakeStoreProductDto.class);
      return toGenericProductDto(responseEntity.getBody());
    }

    @Override
    public void updateProductById(int id) {

    }
}
