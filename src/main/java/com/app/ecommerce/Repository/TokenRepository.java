package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecommerce.Model.AuthenticationToken;
import com.app.ecommerce.Model.User;


public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>
{
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
       
}
