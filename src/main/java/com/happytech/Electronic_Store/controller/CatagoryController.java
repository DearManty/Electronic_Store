package com.happytech.Electronic_Store.controller;

import com.happytech.Electronic_Store.dto.*;
import com.happytech.Electronic_Store.entity.Catagory;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.service.CatagoryService;
import com.happytech.Electronic_Store.service.FileService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/catagories")
public class CatagoryController {
    Logger logger= LoggerFactory.getLogger(CatagoryController.class);
    @Autowired
    private CatagoryService catagoryService;

    @Autowired
    private FileService fileService;

    @Value("${catagory.profile.image.paths}")
    private String imageUploadPath;
    // CREATE
    @PostMapping
    public ResponseEntity<CatagoryDto> createCatagory(@Valid @RequestBody CatagoryDto catagoryDto) {
        logger.info("Intiating request for Controller create catagory");
        CatagoryDto createCatagory = this.catagoryService.createCatagory(catagoryDto);
        logger.info("completed request for Controller create catagory");
        return new ResponseEntity<CatagoryDto>(createCatagory, HttpStatus.CREATED);

    }

    // UPDATE
    @PutMapping("/update/{catagoryId}")
    public ResponseEntity<CatagoryDto> updateCatagory(@Valid @RequestBody CatagoryDto catagoryDto,
                                                      @PathVariable int catagoryId) {
        logger.info("Intiating request for Controller update catagory");
        CatagoryDto updateCatagory = this.catagoryService.updateCatagory(catagoryDto, catagoryId);
        logger.info("completed request for Controller update catagory");
        return new ResponseEntity<CatagoryDto>(updateCatagory, HttpStatus.OK);

    }

    // DELETE
    @DeleteMapping("/delete/{catagoryId}")
    public ResponseEntity<ApiResposnse> deleteCatagory(@Valid @PathVariable int catagoryId) {
        logger.info("Intiating request for Controller delete catagory");
        this.catagoryService.deleteCatagory(catagoryId);
        ApiResposnse response = ApiResposnse.builder().messege(AppConstant.DELETE_CATAGORY).status(HttpStatus.OK)
                .success(true).build();
        logger.info("completed request for Controller delete catagory");
        return new ResponseEntity<ApiResposnse>(response, HttpStatus.OK);
    }

    // getAllCatagory
    @GetMapping("/")
    public ResponseEntity<PageableResponse<CatagoryDto>> getAllCatagory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        logger.info("Intiating request for Controller getAll catagory");
        PageableResponse<CatagoryDto> allCatagory = this.catagoryService.getAllCatagory(pageNumber, pageSize, sortBy,
                sortDir);
        logger.info("completed request for Controller getAll catagory");
        return new ResponseEntity<PageableResponse<CatagoryDto>>(allCatagory, HttpStatus.OK);

    }
    //getSingleCatagory
    @GetMapping("/get/{catagoryId}")
    public ResponseEntity<CatagoryDto> getSingleCatagory(@Valid @PathVariable int catagoryId) {
        logger.info("Intiating request for Controller getSingle catagory");
        CatagoryDto singleCatagory = this.catagoryService.getSingleCatagory(catagoryId);
        logger.info("completed request for Controller getSingle catagory");
        return new ResponseEntity<CatagoryDto>(singleCatagory, HttpStatus.OK);

    }
    //searchCatagory
    @GetMapping("/gets/{keyword}")
    public ResponseEntity<List<CatagoryDto>> searchCatagory(@Valid @PathVariable String keyword) {
        logger.info("Intiating request for Controller search catagory");
        List<CatagoryDto> searchByTitle = this.catagoryService.searchByTitle(keyword);
        logger.info("completed request for Controller search catagory");
        return new ResponseEntity<List<CatagoryDto>>(searchByTitle, HttpStatus.OK);

    }
    @PostMapping("/image/{catagoryId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image,
                                                         @PathVariable int catagoryId) throws IOException {
        logger.info("Initiating request for Controller uploadImage");
        String imageName = this.fileService.uploadFile(image, imageUploadPath);

        CatagoryDto catagoryDto=catagoryService.getSingleCatagory(catagoryId);
        catagoryDto.setCoverImage(imageName);
        CatagoryDto updateCatagory = catagoryService.updateCatagory(catagoryDto, catagoryId);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName)
                .messege("Image is Successfully Saved").success(true).status(HttpStatus.CREATED).build();
        logger.info("Completed request for Controller uploadImage");
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    // serve user Image
    @GetMapping("/images/{catagoryId}")
    public void serveUserImage(@PathVariable int catagoryId, HttpServletResponse response) throws IOException {
        CatagoryDto catagory = this.catagoryService.getSingleCatagory(catagoryId);
        logger.info("Catagory Image Name:" + catagory.getCoverImage());
        InputStream resource = fileService.getResource(imageUploadPath, catagory.getCoverImage());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }
}
