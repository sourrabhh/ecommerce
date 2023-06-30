package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecommerce.Model.AuthenticationToken;




public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>
{
    
}
