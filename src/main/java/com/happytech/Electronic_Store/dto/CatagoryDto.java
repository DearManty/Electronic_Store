package com.happytech.Electronic_Store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    private String coverImage;
}
