package com.app.MBox.Dto;

import com.app.MBox.aditional.passwordMatches;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@passwordMatches
public class userDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    // standard getters and setters
}

