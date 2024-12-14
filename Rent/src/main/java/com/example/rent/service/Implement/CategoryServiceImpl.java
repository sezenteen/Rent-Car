package com.example.rent.service.Implement;

import com.example.rent.model.Category;
import com.example.rent.repository.CategoryRepository;
import com.example.rent.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findByCategoryName(String name) {return categoryRepository.findAll();
    }



    @Override
    // Fetch all categories from the database
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> createCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(Category category) {
        Category findCategory= categoryRepository.findById(category.getId()).orElse(null);

        findCategory.setName(category.getName());


        return categoryRepository.save(findCategory);
    }

    @Override
    public String deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return id+"-тай Хэрэглэгчийн мэдээлэл устлаа.";
    }
}
