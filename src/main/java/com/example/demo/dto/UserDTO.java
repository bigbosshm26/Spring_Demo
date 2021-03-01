package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class UserDTO {

    @JsonProperty("accountName")
    private String accountName;

    @JsonIgnore
    private String password;

    @JsonProperty("email")
    private String email;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonValue
    public String toUserInfo(){
        return "{accountName:" + accountName + ","+"email:" + email +"}";
    }

    public UserDTO(){};

    public UserDTO(String accountName, String email) {
        this.accountName = accountName;
        this.email = email;
    }



    @Override
    public String toString() {
        return "UserDTO{" +
            "accountName='" + accountName + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
