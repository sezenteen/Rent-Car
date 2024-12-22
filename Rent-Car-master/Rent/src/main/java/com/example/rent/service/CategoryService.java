package com.example.rent.service;

import com.example.rent.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findByCategoryName(String name);

    List<Category> getAllCategories();

    //    Neg hereglechiin medeelel hadgalah
    public Category createCategory(Category category);

    //    Olon hereglegchiin medeelel hadgalah
    List<Category> createCategories(List<Category> categories);

    //    Neg hereglegchiin medeelel avah
    public Optional<Category> getCategoryById(Long id);

    //    neg hereglegchiin medeelel zasvarlah
    public Category updateCategory(Category category);

    //    Neg hereglegchiin medeelel ustgah
    public String deleteCategoryById(Long id);

}
