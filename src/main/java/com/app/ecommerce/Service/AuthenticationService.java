package com.app.ecommerce.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.Exception.AuthenticationFailException;
import com.app.ecommerce.Model.AuthenticationToken;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Repository.TokenRepository;

@Service
public class AuthenticationService 
{
    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token)
    {
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);

        if(Objects.isNull(authenticationToken))
        {
            return null;
        }
            return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException
    {
        // Null Check
        if(Objects.isNull(token))
        {   // Throw Exception 
            throw new AuthenticationFailException("Token Not Present");
        }

        if(Objects.isNull(getUser(token)))
        {
            throw new AuthenticationFailException(" No User Present for token,  Token Not Valid");
        }
    }
}
