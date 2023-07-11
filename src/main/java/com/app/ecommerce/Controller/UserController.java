package com.app.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.DTO.ResponseDto;
import com.app.ecommerce.DTO.UserDto.SignupDto;
import com.app.ecommerce.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/user")
@RestController
public class UserController 
{
    @Autowired
    UserService userService;
    // Sign up API
    @PostMapping(path = "/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto)
    {
        return userService.signUp(signupDto);      
    }

    //SignUp Api
    
}
