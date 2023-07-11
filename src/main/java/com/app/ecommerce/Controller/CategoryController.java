package com.app.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.Model.Category;
import com.app.ecommerce.Service.CategoryService;

@RestController
public class CategoryController 
{
    private CategoryService categoryService;
    // private CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/create")
    public String createCategoty(@RequestBody Category category)
    {
        categoryService.createCategory(category);
        return "Success";      
    }

    @GetMapping(path = "/list")
    public List<Category> listCategory()
    {
        return categoryService.listCategory();       
    }

    @PostMapping(path = "update/{categoryId}")
    public Category updateCategory(@PathVariable int categoryId, @RequestBody Category category)
    {
        return categoryService.editCategory(categoryId, category);
    }
}
