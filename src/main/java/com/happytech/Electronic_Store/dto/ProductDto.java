package com.happytech.Electronic_Store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer productId;

    private String title;
    @Column(length =10000 )
    private String description;

    private Integer price;

    private Integer discountedPrice;

    private  Integer quantity;

    private boolean live;

    private  boolean stock;


}
