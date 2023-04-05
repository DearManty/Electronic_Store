package com.happytech.Electronic_Store.service;

import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    //create

    ProductDto createProduct(ProductDto productDto);

    //update

    ProductDto updateProduct(ProductDto productDto,Integer productId);

    //getSingle

    ProductDto getSingleProduct(Integer productId);

    //  getAllProduct

    PageableResponse<ProductDto> getAllProduct();

    //delete

    void deleteProduct(Integer productId);

    //search product

    PageableResponse<ProductDto> searchByTitle(String subTitle);

    //getAll Live

    PageableResponse<ProductDto> getAllLive();
}
