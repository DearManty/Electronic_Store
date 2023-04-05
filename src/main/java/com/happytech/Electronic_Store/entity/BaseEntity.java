package com.happytech.Electronic_Store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Column(name="create_Date",updatable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name="update_Date",insertable = false)
    @UpdateTimestamp
    private  LocalDate updateDate;

    @Column(name="isActive")
    private String isActive;

}
