package com.app.ecommerce.CommonAPIResponse;

import java.time.LocalDateTime;

public class ApiResponse 
{
    private final boolean Success;
    private final String message;

    public ApiResponse(boolean success, String message) {
        Success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return Success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp()
    {
        return LocalDateTime.now().toString();
    }  
}
