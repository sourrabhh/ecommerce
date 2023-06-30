package com.app.ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.Model.Category;
import com.app.ecommerce.Repository.CategoryRepository;

@Service
public class CategoryService 
{
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> listCategory()
    {
        return categoryRepository.findAll();
    } 

    public void createCategory(Category category)
    {
        categoryRepository.save(category);
    }

    public Category editCategory(int categoryId, Category updateCategory) 
    {
        Category category = categoryRepository.findById(categoryId).get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImgURL(updateCategory.getImgURL());

        return categoryRepository.save(category);
    }
}
