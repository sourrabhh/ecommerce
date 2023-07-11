package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.app.ecommerce.Model.Category;



// @Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> 
{

    
} 
