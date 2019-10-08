package com.epam.news.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    private User user;

    @Size( max=100, message="name must be less than 100 characters")
    @NotNull(message = "Please provide a name")
    private String username;

    @Size( max=8, message="password must be less than 8 characters")
    @NotNull(message = "Please provide a password")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])" +
            "(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "password should contains letters and  numbers")
    private String password;

    public User getUser() {
        return new User(username, password);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
