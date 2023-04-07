package com.happytech.Electronic_Store.service.Impl;

import com.happytech.Electronic_Store.controller.ProductController;
import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.ProductDto;
import com.happytech.Electronic_Store.entity.Product;
import com.happytech.Electronic_Store.exception.ResourceNotFoundException;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.helper.Helper;
import com.happytech.Electronic_Store.repository.ProductRepository;
import com.happytech.Electronic_Store.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        logger.info("Intiating request for Controller create product");
       Product product= modelMapper.map(productDto, Product.class);
        Product savedproduct = this.productRepository.save(product);
        ProductDto productDto1 = modelMapper.map(savedproduct, ProductDto.class);
        logger.info("Completed request for Controller create product");
        return productDto1;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer productId) {
        logger.info("Intiating request for Controller update product"+productId);
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
        logger.info("Completed request for Controller update product"+productId);
        return productDto1;
    }

    @Override
    public ProductDto getSingleProduct(Integer productId) {
        logger.info("Intiating request for Controller get Single product"+productId);
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_EXCEPTION + productId));
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        logger.info("Completed request for Controller get Single product"+productId);
        return productDto;
    }

    @Override
    public PageableResponse<ProductDto> getAllProduct(int pageNumber,int pageSize,String sortBy,String sortDir) {
        logger.info("Intiating request for Controller getAll product");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        PageRequest pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> page = this.productRepository.findAll(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        logger.info("Completed request for Controller getAll product");

        return pageableResponse;
    }

    @Override
    public void deleteProduct(Integer productId) {
        logger.info("Intiating request for Controller delete product"+productId);
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_EXCEPTION + productId));
        this.productRepository.delete(product);
        logger.info("Completed request for Controller delete product"+productId);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir) {
        logger.info("Intiating request for Controller search product");
Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> byTitleContaining = this.productRepository.findByTitleContaining(subTitle, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(byTitleContaining,ProductDto.class);
        logger.info("Completed request for Controller search product");
        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir) {
        logger.info("Intiating request for Controller getAllLlive ");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = this.productRepository.findByLiveTrue(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        logger.info("Completed request for Controller getAllLlive");
        return pageableResponse;
    }
}
