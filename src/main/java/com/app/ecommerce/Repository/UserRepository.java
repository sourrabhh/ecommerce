package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}
