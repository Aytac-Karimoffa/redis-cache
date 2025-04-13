package com.example.rediscache.service;

import com.example.rediscache.dto.ProductDto;
import com.example.rediscache.entity.Product;
import com.example.rediscache.exception.ProductNotFoundException;
import com.example.rediscache.repository.PraductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductServic {

    public static final String PRUDUCT_CACHE ="products";


    private final PraductRepository praductRepository;

    public ProductServic(PraductRepository praductRepository) {
        this.praductRepository = praductRepository;
    }

    @CachePut(value = PRUDUCT_CACHE, key = "#result.id()")
    public ProductDto createProduct(ProductDto productDto){
        var product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product saveProduct = praductRepository.save(product);
        return new ProductDto(saveProduct.getId(), saveProduct.getName(), saveProduct.getPrice());

    }

    @Cacheable(value = PRUDUCT_CACHE, key = "#productId")
    public ProductDto getProduct(Long productId) {
        Product product = praductRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with " + productId + " Not found"));
        return new ProductDto(product.getId(), product.getName(),product.getPrice());

    }

    @CachePut(value = PRUDUCT_CACHE, key = "#resultId")
    public ProductDto updateProduct(ProductDto productDto){
        Long productId = productDto.id();
        Product product = praductRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + "not found"));

        product.setPrice(productDto.price());
        product.setName(product.getName());

        Product updateProduct = praductRepository.save(product);
        return new ProductDto(updateProduct.getId(), updateProduct.getName(), updateProduct.getPrice());


    }
    @CacheEvict(value = PRUDUCT_CACHE, key = "#productId")
    public void deletProduct (Long productId){
        Product product = praductRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product with " + " Not found"));
        praductRepository.delete(product);
    }



}
