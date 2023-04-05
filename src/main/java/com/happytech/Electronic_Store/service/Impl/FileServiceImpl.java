package com.happytech.Electronic_Store.service.Impl;

import com.happytech.Electronic_Store.exception.BadApiRequest;
import com.happytech.Electronic_Store.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {

    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
  logger.info("FileName"+originalFilename);
        String filename= UUID.randomUUID().toString();

        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fillleNameWithExtention=filename+extention;
        String fullPathWithFileName=path+ File.separator+fillleNameWithExtention;

        //logger.info("FullPathWithName"+fullPathWithFileName);
        if(extention.equalsIgnoreCase(".png")||extention.equalsIgnoreCase(".jpg")||extention.equalsIgnoreCase(".jpeg")) {

            // file save
            //logger.info("File Extention is"+extention);
            File folder=new File(path);
            if(!folder.exists()) {
                // create folder
                folder.mkdirs();

                //upload
                Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));

                return fillleNameWithExtention;
            }

        }else {
            throw new BadApiRequest("File with this"+extention+"not allowed !!");

        }


        return fullPathWithFileName;
    }


    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        logger.info("Intiating request for Service getResponse");
        String fullpath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullpath);
        logger.info("completed request for Service getResponse");
        return inputStream;
    }

}
