package com.happytech.Electronic_Store.controller;

import com.happytech.Electronic_Store.dto.ApiResposnse;
import com.happytech.Electronic_Store.dto.CatagoryDto;
import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.service.CatagoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/catagories")
public class CatagoryController {
    Logger logger= LoggerFactory.getLogger(CatagoryController.class);
    @Autowired
    private CatagoryService catagoryService;

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
}
