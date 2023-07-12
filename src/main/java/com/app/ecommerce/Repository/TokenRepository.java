package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.Model.AuthenticationToken;
import com.app.ecommerce.Model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>
{
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);     
}