package com.app.MBox.dto;

import com.app.MBox.aditional.passwordMatches;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class changePasswordDto {



    @Length(min = 8 , max = 64)
    private String password;

    @Length(min = 8 , max = 64)
    private String newPassword;

    @Length(min = 8 , max = 64)
    private String confirmPassword;


}
