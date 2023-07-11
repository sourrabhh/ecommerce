package com.app.ecommerce.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.ProductDto;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Model.Wishlist;
import com.app.ecommerce.Repository.WishlistRepository;


@Service
public class WishlistService 
{
    @Autowired 
    WishlistRepository wishlistRepository;

    @Autowired
    ProductService productService; 

    public void createWishlist(Wishlist wishlist) 
    {
        wishlistRepository.save(wishlist);
    }

    public List<ProductDto> getWishlistForUser(User user) 
    {
        final List<Wishlist> wishlists=  wishlistRepository.findAllByUser(user);

        List<ProductDto> productsDtos = new ArrayList<>();

        for(Wishlist wishlist: wishlists)
        {
            productsDtos.add(productService.getProductDto(wishlist.getProduct()));
        }
        return productsDtos;
    }

}