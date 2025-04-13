package com.example.rediscache.config;

import com.example.rediscache.dto.ProductDto;
import com.example.rediscache.service.ProductServic;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductServic productServic;

    public ProductController(ProductServic productServic) {
        this.productServic = productServic;
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductDto> getProduct (@PathVariable Long productId){
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(productServic.getProduct(productId));

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Data has been created successfully")
    public ProductDto createProduct(@RequestBody @Valid ProductDto productDto){
        return productServic.createProduct(productDto);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto updateProduct (@RequestBody ProductDto productDto){
        return productServic.updateProduct(productDto);
    }

    @DeleteMapping(path = "/{productId}")
    @ResponseStatus(value = HttpStatus.OK,reason = "Succesfully deleted")
    public void deletProduct (@PathVariable Long productId){
        productServic.deletProduct(productId);
    }


}
