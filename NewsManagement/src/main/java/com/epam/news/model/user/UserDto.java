package com.epam.news.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    private User user;

    @Size(max = 100, message = "{validation.userDto.name.size}")
    @NotNull(message = "{validation.userDto.name.notNull}")
    private String username;

    @NotNull(message = "{validation.userDto.password.notNull}")
    @Pattern(regexp =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "{validation.userDto.password.pattern}")
    private String password;

    public UserDto(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto() {
    }

    public User getUser() {
        return new User(username, password);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
