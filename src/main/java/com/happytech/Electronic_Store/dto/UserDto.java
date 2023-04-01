package com.happytech.Electronic_Store.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String Id;


    private String name;
    //@Pattern(regexp = "^(a-z0-9)[a-z0-9]",message="Invalid User Email")

    private String email;
    @NotBlank(message = "Password is Needed")
    private String password;
    @Size(min = 4, max = 6, message = "Invalid gender")
    private String gender;
    @NotBlank(message = "Write something about yourself")
    private String about;
    //@ImageNameValid
    private String image;
}
