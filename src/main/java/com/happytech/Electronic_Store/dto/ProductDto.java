package com.happytech.Electronic_Store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer productId;


    @NotBlank
    @Size(min = 4, max = 15, message = "Title mustbe min 4 and max 15 characters")
    private String title;

    @NotBlank(message = "Description requried")
    private String description;

    @NotBlank
    private Integer price;

    private Integer discountedPrice;

    private  Integer quantity;

    private boolean live;

    private  boolean stock;


}
