package com.happytech.Electronic_Store.service;

import com.happytech.Electronic_Store.dto.CatagoryDto;
import com.happytech.Electronic_Store.dto.PageableResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatagoryService {
    // create
    CatagoryDto createCatagory(CatagoryDto catagoryDto);

    // update
    CatagoryDto updateCatagory(CatagoryDto catagoryDto, int catagoryId);

    // delete
    void deleteCatagory(int catagoryId);

    // getAll
    PageableResponse<CatagoryDto> getAllCatagory(int pageNumber, int pageSize, String sortBy, String sortDir);

    // getSingle
    CatagoryDto getSingleCatagory(int catagoryId);

    //searchCatagory

    List<CatagoryDto> searchByTitle(String keyword);
}
