package com.app.ecommerce.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.ecommerce.Model.Wishlist;
import com.app.ecommerce.Repository.WishlistRepository;


@Service
public class WishlistService 
{
    @Autowired 
    WishlistRepository wishlistRepository;

    public void createWishlist(Wishlist wishlist) 
    {
        wishlistRepository.save(wishlist);
    }
   
   
    


}
