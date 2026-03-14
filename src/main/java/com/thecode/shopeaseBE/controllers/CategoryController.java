package com.thecode.shopeaseBE.controllers;

import com.thecode.shopeaseBE.dto.CategoryDto;
import com.thecode.shopeaseBE.entities.Category;
import com.thecode.shopeaseBE.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")


public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id",required = true) UUID categoryId) {
        Category category = categoryService.getCategory(categoryId) ;
        return new ResponseEntity<>(category,  HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto) ;
        return new ResponseEntity<>(category, HttpStatus.CREATED) ;
    }


}

