package com.happytech.Electronic_Store.service.Impl;

import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.ProductDto;
import com.happytech.Electronic_Store.entity.Product;
import com.happytech.Electronic_Store.exception.ResourceNotFoundException;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.repository.ProductRepository;
import com.happytech.Electronic_Store.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
       Product product= modelMapper.map(productDto, Product.class);
        Product savedproduct = this.productRepository.save(product);
        ProductDto productDto1 = modelMapper.map(savedproduct, ProductDto.class);

        return productDto1;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer productId) {

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_EXCEPTION + productId));
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setTitle(productDto.getTitle());
        product.setStock(productDto.isStock());
        product.setLive(productDto.isLive());
        Product save = this.productRepository.save(product);
        ProductDto productDto1 = modelMapper.map(save, ProductDto.class);
        return productDto1;
    }

    @Override
    public ProductDto getSingleProduct(Integer productId) {
        return null;
    }

    @Override
    public PageableResponse<ProductDto> getAllProduct() {
        return null;
    }

    @Override
    public void deleteProduct(Integer productId) {

    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle) {
        return null;
    }

    @Override
    public PageableResponse<ProductDto> getAllLive() {
        return null;
    }
}
