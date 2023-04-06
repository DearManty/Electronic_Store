package com.happytech.Electronic_Store.controller;

import com.happytech.Electronic_Store.dto.ApiResposnse;
import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.ProductDto;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    Logger logger= LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    //create
    @PostMapping("/")

    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        logger.info("Intiating request for Controller create product");
        ProductDto product = this.productService.createProduct(productDto);
        logger.info("Completed request for Controller create product");
        return new ResponseEntity<ProductDto>(product, HttpStatus.CREATED);
    }

    //update

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Integer productId){
        logger.info("Intiating request for Controller update product"+productId);
        ProductDto productDto1 = this.productService.updateProduct(productDto, productId);
        logger.info("Completed request for Controller update product"+productId);
        return  new ResponseEntity<ProductDto>(productDto1,HttpStatus.OK);
    }

    //getSingleProduct

    @GetMapping("get/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@Valid @PathVariable Integer productId){
        logger.info("Intiating request for Controller get Single product"+productId);
        ProductDto singleProduct = this.productService.getSingleProduct(productId);
        logger.info("Completed request for Controller get Single product"+productId);
        return  new ResponseEntity<ProductDto>(singleProduct,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        logger.info("Intiating request for Controller getAll product");
        PageableResponse<ProductDto> allProduct = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Completed request for Controller getAll product");
        return new ResponseEntity<PageableResponse<ProductDto>>(allProduct,HttpStatus.OK);
    }
    //deleteProduct

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResposnse> deleteProduct(@Valid @PathVariable Integer productId){
        logger.info("Intiating request for Controller delete product"+productId);
        this.productService.deleteProduct(productId);
        ApiResposnse build = ApiResposnse.builder().messege(AppConstant.DELETE_PRODUCT).success(true).status(HttpStatus.OK).build();
        logger.info("Completed request for Controller delete product"+productId);
        return new ResponseEntity<ApiResposnse>(build,HttpStatus.OK);
    }
//getAllLive
    @GetMapping("/getAllLive")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(

            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        logger.info("Intiating request for Controller getAllLlive ");
        PageableResponse<ProductDto> allLive = this.productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Completed request for Controller getAllLlive");
        return  new ResponseEntity<PageableResponse<ProductDto>>(allLive,HttpStatus.OK);
    }

    //searchByTitle
    @GetMapping ("/search/{subTitle}")
    public ResponseEntity<PageableResponse<ProductDto>> searchByTitle(@Valid @PathVariable String subTitle,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        logger.info("Intiating request for Controller search product");
        PageableResponse<ProductDto> productDtoPageableResponse = this.productService.searchByTitle(subTitle, pageNumber, pageSize, sortBy, sortDir);
        logger.info("Completed request for Controller search product");
return new ResponseEntity<PageableResponse<ProductDto>>(productDtoPageableResponse,HttpStatus.OK);
    }
}
