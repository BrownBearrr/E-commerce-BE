package com.thecode.shopeaseBE.controllers;

import com.thecode.shopeaseBE.dto.ProductDto;
import com.thecode.shopeaseBE.entities.Product;
import com.thecode.shopeaseBE.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService ;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts() ;
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // create Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.addProduct(productDto) ;
        return null ;
    }
}
