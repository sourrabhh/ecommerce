package com.app.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.ecommerce.Model.User;
import com.app.ecommerce.Model.Wishlist;

@EnableJpaRepositories
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>
{
    List<Wishlist> findAllByUser(User user);
}