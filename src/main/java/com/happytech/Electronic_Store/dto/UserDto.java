package com.happytech.Electronic_Store.dto;

import com.happytech.Electronic_Store.validate.ImageNameValid;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String Id;


    private String name;
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message="Invalid User Email")

    private String email;
    @NotBlank(message = "Password is Needed")
    private String password;
    @Size(min = 4, max = 6, message = "Invalid gender")
    private String gender;
    @NotBlank(message = "Write something about yourself")
    private String about;
    @ImageNameValid
    private String image;

    private LocalDate createDate;

    private  LocalDate updateDate;

   @NotBlank
    private String isActive;
}
