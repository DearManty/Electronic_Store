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
public class Catagory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int catagoryId;
    @Column(name="catagory_title",length=60,nullable=false)
    private String title;
    @Column(name="catagory_desc",length=50)
    private String description;

    private String coverImage;
}
