package com.app.ecommerce.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.CommonAPIResponse.ApiResponse;
import com.app.ecommerce.DTO.ProductDto;
import com.app.ecommerce.Model.Category;
import com.app.ecommerce.Repository.CategoryRepository;
import com.app.ecommerce.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController 
{
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;
    
    @PostMapping(path = "/addproduct")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto)
    {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        if(!optionalCategory.isPresent())
        {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping(path = "/allproducts")
    public ResponseEntity<List<ProductDto>> getProducts()
    {
        List<ProductDto> productDtos =  productService.getAllProducts();

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    } 

    @PostMapping(path = "/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto) throws Exception
    {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        if(!optionalCategory.isPresent())
        {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.ACCEPTED);
    }
}
