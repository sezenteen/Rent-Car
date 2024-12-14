package com.example.rent.controller;

import com.example.rent.model.Category;
import com.example.rent.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // List all categories
    @GetMapping
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories"; // Refers to a Thymeleaf template `categories.html`
    }

    // Show category by ID
    @GetMapping("/category/{id}")
    public String category(Model model, @PathVariable("id") Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
        } else {
            model.addAttribute("error", "Category not found");
        }
        return "category/category-details";
    }

    // Show form to create a new category
    @GetMapping("/category/new")
    public ModelAndView createCategoryForm(ModelAndView modelAndView) {
        Category category = new Category();
        modelAndView.addObject("category", category);
        modelAndView.setViewName("category/category-form");
        return modelAndView;
    }

    // Handle form submission for creating a category
    @PostMapping("/category")
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category/category-form";
        }
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    // Show form to edit an existing category
    @GetMapping("/category/edit/{id}")
    public ModelAndView editCategoryForm(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            modelAndView.addObject("category", category.get());
            modelAndView.setViewName("category/category-form");
        } else {
            modelAndView.setViewName("error/404");
        }
        return modelAndView;
    }

    // Handle form submission for updating a category
    @PostMapping("/category/edit/{id}")
    public String updateCategory(@PathVariable("id") Long id,
                                 @Valid @ModelAttribute("category") Category category,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "category/category-form";
        }
        category.setId(id); // Ensure the ID is set for the updated category
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    // Delete category by ID
    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }

    // Search categories by name
    @GetMapping("/category/search")
    public String searchCategories(@RequestParam("name") String name, Model model) {
        List<Category> categories = categoryService.findByCategoryName(name);
        if (categories.isEmpty()) {
            model.addAttribute("error", "No categories found with name: " + name);
        } else {
            model.addAttribute("categories", categories);
        }
        return "category/categories";
    }
}
