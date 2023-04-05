package com.happytech.Electronic_Store.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "user_name")
    private String name;

    private String gender;
    @Column(name = "user_email", unique = true)
    private String email;
    @Column(name = "user_password", length = 10)
    private String password;
    @Column(name = "user_gender")
    private String about;
    @Column(name = "user_image_name")
    private String image;
}
