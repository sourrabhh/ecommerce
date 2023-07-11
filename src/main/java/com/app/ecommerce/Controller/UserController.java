package com.app.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.DTO.ResponseDto;
import com.app.ecommerce.DTO.UserDto.SignInDto;
import com.app.ecommerce.DTO.UserDto.SignInReponseDto;
import com.app.ecommerce.DTO.UserDto.SignupDto;
import com.app.ecommerce.Exception.CustomException;
import com.app.ecommerce.Service.UserService;


@RequestMapping("/user")
@RestController
public class UserController 
{
    @Autowired
    UserService userService;

    // Sign up API
    @PostMapping(path = "/signup")
    public ResponseDto SignUp(@RequestBody SignupDto signupDto) throws CustomException 
    {
        return userService.signup(signupDto);      
    }

    @PostMapping("/signin")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    } 
}
