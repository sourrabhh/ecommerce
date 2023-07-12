package com.app.ecommerce.Exception;


public class ProductNotExistException extends IllegalArgumentException
{
    public ProductNotExistException(String msg)
    {
        super(msg);
    }
}