package com.app.ecommerce.DTO.UserDto;


import com.fasterxml.jackson.annotation.JsonProperty;


public class SignupDto 
{
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public SignupDto() {
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    } 
       
}
