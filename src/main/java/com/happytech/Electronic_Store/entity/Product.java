package com.happytech.Electronic_Store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
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
