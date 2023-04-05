package com.happytech.Electronic_Store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="catagories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catagory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int catagoryId;
    @Column(name="catagory_title",length=60,nullable=false)
    private String title;
    @Column(name="category_desc",length=50)
    private String description;
    @Column(name = "category_image_name")
    private String coverImage;
}
