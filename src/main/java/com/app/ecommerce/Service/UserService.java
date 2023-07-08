package com.app.ecommerce.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.ResponseDto;
import com.app.ecommerce.DTO.UserDto.SignInDto;
import com.app.ecommerce.DTO.UserDto.SignInReponseDto;
import com.app.ecommerce.DTO.UserDto.SignupDto;
import com.app.ecommerce.Exception.AuthenticationFailException;
import com.app.ecommerce.Exception.CustomException;
import com.app.ecommerce.Model.AuthenticationToken;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Repository.UserRepository;

import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService 
{
    @Autowired
    UserRepository userRepository;
    
     @Autowired
    AuthenticationService authenticationService;
    
    public ResponseDto signup(SignupDto signupDto) throws CustomException
    { 
        // check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // we have an user
            throw new CustomException("user already present");
        }

        // hash the password
        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword);

        userRepository.save(user);

        // save the user
        // create the token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);
        
        ResponseDto responseDto = new ResponseDto("success", "user created succesfully");
        return responseDto;
    }


    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInReponseDto signIn(SignInDto signInDto1) {
        // find user by email

        User user = userRepository.findByEmail(signInDto1.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }

        // hash the password
        try {
            if (!user.getPassword().equals(hashPassword(signInDto1.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // compare the password in DB
        // if password match

        AuthenticationToken token = authenticationService.getToken(user);

        // retrive the token

        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }

        return new SignInReponseDto("sucess", token.getToken());
        // return response
    }
}



