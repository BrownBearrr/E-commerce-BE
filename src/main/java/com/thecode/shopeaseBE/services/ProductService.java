package com.thecode.shopeaseBE.services;

import com.thecode.shopeaseBE.dto.ProductDto;
import com.thecode.shopeaseBE.entities.Product;

import java.util.List;


public interface ProductService {

    public Product addProduct(ProductDto product) ;

    public List<Product> getAllProducts() ;


}

