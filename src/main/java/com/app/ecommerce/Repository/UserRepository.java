package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecommerce.Model.User;


public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}
