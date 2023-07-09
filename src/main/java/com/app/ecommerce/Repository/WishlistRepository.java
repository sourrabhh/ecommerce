package com.app.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.Model.User;
import com.app.ecommerce.Model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>
{
    List<Wishlist> findAllByUser(User user);
}
