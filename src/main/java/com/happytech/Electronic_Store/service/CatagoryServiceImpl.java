package com.happytech.Electronic_Store.service;

import com.happytech.Electronic_Store.dto.CatagoryDto;
import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.entity.Catagory;
import com.happytech.Electronic_Store.exception.ResourceNotFoundException;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.helper.Helper;
import com.happytech.Electronic_Store.repository.CatagoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatagoryServiceImpl implements CatagoryService
{
Logger logger= LoggerFactory.getLogger(CatagoryServiceImpl.class);
    @Autowired
    private CatagoryRepository catagoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CatagoryDto createCatagory(CatagoryDto catagoryDto) {
        logger.info("Intiating request for Service create  catagory");
        Catagory catagory = modelMapper.map(catagoryDto, Catagory.class);
        Catagory catagory2 = this.catagoryRepository.save(catagory);
        CatagoryDto catagoryDto2 = modelMapper.map(catagory2, CatagoryDto.class);
        logger.info("completed request for Service create  catagory");
        return catagoryDto2;
    }

    @Override
    public CatagoryDto updateCatagory(CatagoryDto catagoryDto, int catagoryId) {
        logger.info("Intiating request for Service update  catagory");
        Catagory catagory = this.catagoryRepository.findById(catagoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION + catagoryId));
        catagory.setTitle(catagoryDto.getTitle());
        catagory.setDescription(catagoryDto.getDescription());
        catagory.setCoverImage(catagoryDto.getCoverImage());
        Catagory updatedCatagory = this.catagoryRepository.save(catagory);
        CatagoryDto catagoryDto2 = modelMapper.map(updatedCatagory, CatagoryDto.class);
        logger.info("completed request for Service update  catagory");
        return catagoryDto2;
    }


    @Override
    public void deleteCatagory(int catagoryId) {
        logger.info("Intiating request for Service delete  catagory");
        Catagory catagory = this.catagoryRepository.findById(catagoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION + catagoryId));
        this.catagoryRepository.delete(catagory);
        logger.info("completed request for Service update  catagory");
    }

    @Override
    public PageableResponse<CatagoryDto> getAllCatagory(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Intiating request for Service getAll  catagory");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Catagory> page = this.catagoryRepository.findAll(pageable);
        PageableResponse<CatagoryDto> pageableResponse = Helper.getPageableResponse(page, CatagoryDto.class);
        logger.info("completed request for Service getAll  catagory");
        return pageableResponse;
    }

    @Override
    public CatagoryDto getSingleCatagory(int catagoryId) {
        logger.info("Intiating request for Service getSingle  catagory");
        Catagory catagory = this.catagoryRepository.findById(catagoryId)
                .orElseThrow(() -> new ResourceNotFoundException());
        CatagoryDto catagoryDto = modelMapper.map(catagory, CatagoryDto.class);
        logger.info("completed request for Service getSingle  catagory");
        return catagoryDto;
    }

    @Override
    public List<CatagoryDto> searchByTitle(String keyword) {
        logger.info("Intiating request for Service search  catagory");
        List<Catagory> findByTitle = this.catagoryRepository.findByTitle(keyword);
        List<CatagoryDto> catagoryDto = findByTitle.stream().map((catagory)->modelMapper.map(catagory, CatagoryDto.class)).collect(Collectors.toList());
        logger.info("completed request for Service search  catagory");
        return catagoryDto;
    }

}
