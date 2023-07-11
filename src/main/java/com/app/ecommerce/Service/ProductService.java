package com.app.ecommerce.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.ProductDto;
import com.app.ecommerce.Model.Category;
import com.app.ecommerce.Model.Product;
import com.app.ecommerce.Repository.ProductRepository;

@Service
public class ProductService 
{
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) 
    {
        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImgURL(productDto.getImgURL());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product)
    {
        ProductDto productDto = new ProductDto();

        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setImgURL(product.getImgURL());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());

        productDto.setCategoryId(product.getCategory().getId());
        return productDto;

    }
    
    public List<ProductDto> getAllProducts() 
    {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product:allProducts)
        {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception 
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // Throw Exception if product not present
        if(!optionalProduct.isPresent())
        {
            throw new Exception("Product Not Present");
        }

        Product product = optionalProduct.get();

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImgURL(productDto.getImgURL());
        product.setPrice(productDto.getPrice());

        productRepository.save(product);
    }
}
