package com.happytech.Electronic_Store.repository;

import com.happytech.Electronic_Store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<ProductRepository> findByTitleContaining(String title);

    List<ProductRepository> findByLiveTrue();
}
