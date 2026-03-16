package com.thecode.shopeaseBE.services;

import com.thecode.shopeaseBE.dto.CategoryDto;
import com.thecode.shopeaseBE.dto.CategoryTypeDto;
import com.thecode.shopeaseBE.entities.Category;
import com.thecode.shopeaseBE.entities.CategoryType;
import com.thecode.shopeaseBE.exceptions.ResourceNotFoundEx;
import com.thecode.shopeaseBE.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository ;

    public Category getCategory(UUID categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId) ;
        return category.orElse(null) ;
    }

    public Category createCategory(CategoryDto categoryDto) {
       Category category = mapToEntity(categoryDto) ;
       return categoryRepository.save(category) ;
    }


    private Category mapToEntity (CategoryDto categoryDto) {
        Category category = Category.builder()
                .code(categoryDto.getCode())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build() ;

        if(null != categoryDto.getCategoryTypeList()) {
            List<CategoryType> categoryTypes = mapToCategyTypesList(categoryDto.getCategoryTypeList(),category) ;
            category.setCategoryTypes(categoryTypes);
        }

        return category ;
    }

    private List<CategoryType> mapToCategyTypesList(List<CategoryTypeDto> categoryTypeList , Category category) {
        return categoryTypeList.stream().map(categoryTypeDto -> {
            CategoryType categoryType = new CategoryType() ;
            categoryType.setCode(categoryTypeDto.getCode());
            categoryType.setDescription(categoryTypeDto.getDescription());
            categoryType.setName(categoryTypeDto.getName());
            categoryType.setCategory(category);
            return categoryType ;
        }).collect(Collectors.toList()) ;
    }

    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll() ;
        return categoryList ;
    }

    public Category updateCategory(CategoryDto categoryDto , UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundEx("Category not found with id: " + categoryDto.getId())) ;
        if (categoryDto.getName() != null) {
            category.setName(categoryDto.getName());
        }
        if (categoryDto.getCode() != null) {
            category.setCode(categoryDto.getCode());
        }
        if (categoryDto.getDescription() != null) {
            category.setDescription(categoryDto.getDescription());
        }
        if (categoryDto.getCategoryTypeList() != null) {
            List<CategoryType> categoryTypes = mapToCategyTypesList(categoryDto.getCategoryTypeList(), category);
            category.setCategoryTypes(categoryTypes);
        }
        Category categoryUpdate = categoryRepository.save(category) ;
        return categoryUpdate ;
    }

    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId); ;
    }
}
