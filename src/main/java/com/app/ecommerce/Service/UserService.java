package com.app.ecommerce.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.ResponseDto;
import com.app.ecommerce.DTO.UserDto.SignupDto;
import com.app.ecommerce.Exception.CustomException;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Repository.UserRepository;

import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService 
{
    @Autowired
    UserRepository userRepository;

    public ResponseDto signUp(SignupDto signupDto) 
    {
        if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
        {
            // we have a user
            throw new CustomException("Email Id already used");
        }
        
        // Password Encryption
        String encryptedPassword = signupDto.getPassword();
        if (signupDto.getPassword() == null) 
        {
             throw new IllegalArgumentException("Password cannot be null");
        }

        try{
            encryptedPassword = hashPassword(signupDto.getPassword());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            // throw new CustomException(e.getMessage());
        }

        // Saving User 
        User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(), encryptedPassword);    
        userRepository.save(user);
        // 
        ResponseDto responseDto = new ResponseDto("success", "dummy response");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();

        String myhash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return myhash;
    }    
}
