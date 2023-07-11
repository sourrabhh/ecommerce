package com.app.ecommerce.Exception;

public class AuthenticationFailException  extends IllegalArgumentException
{
    public AuthenticationFailException(String msg){
        super(msg);
    }
}