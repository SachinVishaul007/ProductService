package com.productservice.productservice.services;


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.apache.coyote.Response;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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

        if(responseEntity.getBody() == null)
            throw new ProductNotFoundException("Product with id "+ id+ " not found");
        return toGenericProductDto(responseEntity.getBody());


    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate template = restTempateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = template.getForEntity(genericProductURL, FakeStoreProductDto[].class);
        List<GenericProductDto> result = new ArrayList<>();

        if(responseEntity.getBody() == null)
            throw new ProductNotFoundException("Products not found");
        for (FakeStoreProductDto fakeStoreProductDto : responseEntity.getBody()) {
                result.add(toGenericProductDto(fakeStoreProductDto));
            }


        return result;

    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate template = restTempateBuilder.build();
        RequestCallback requestCallback = template.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = template.responseEntityExtractor(FakeStoreProductDto.class);


        ResponseEntity<FakeStoreProductDto> responseEntity= template.execute(specificProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        if (responseEntity != null && responseEntity.getBody() == null)
            throw new ProductNotFoundException("Product with id " + id + " not found");
        return toGenericProductDto(template.execute(specificProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id).getBody());

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
      RestTemplate template = restTempateBuilder.build();
      ResponseEntity<FakeStoreProductDto> responseEntity = template.postForEntity(genericProductURL, genericProductDto, FakeStoreProductDto.class);
      return toGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        RestTemplate template = restTempateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(genericProductDto, headers);
        ResponseEntity<FakeStoreProductDto> responseEntity = template.exchange(specificProductURL, HttpMethod.PUT,requestEntity,FakeStoreProductDto.class, id);
        if(responseEntity.getBody() == null)
            throw new ProductNotFoundException("Product with id "+ id+ " not found");
        return toGenericProductDto(responseEntity.getBody());

    }
}

//TODO:

//ProductControllerAdvice  -> @ExceptionHandler , ExceptionDto