package com.happytech.Electronic_Store.dto;

import com.happytech.Electronic_Store.validate.ImageNameValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryDto {
    private int catagoryId;

    @NotBlank
    @Size(min = 4, max = 15, message = "Title mustbe min 4 and max 15 characters")
    private String title;

    @NotBlank(message = "Description requried")
    private String description;

    @ImageNameValid
    private String coverImage;

    private LocalDate createDate;

    private  LocalDate updateDate;

    @NotBlank
    private String isActive;
}
