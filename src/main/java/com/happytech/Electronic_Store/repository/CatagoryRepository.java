package com.happytech.Electronic_Store.repository;

import com.happytech.Electronic_Store.entity.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatagoryRepository extends JpaRepository<Catagory,Integer> {
    List<Catagory> findByTitle(String keyword);

}
