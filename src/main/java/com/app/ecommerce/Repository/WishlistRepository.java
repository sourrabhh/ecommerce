package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecommerce.Model.Wishlist;


public interface WishlistRepository extends JpaRepository<Wishlist, Integer>
{
    
}
