package com.happytech.Electronic_Store.controller;

import com.happytech.Electronic_Store.dto.ApiResposnse;
import com.happytech.Electronic_Store.dto.ImageResponse;
import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.UserDto;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.service.FileService;
import com.happytech.Electronic_Store.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.paths}")
    private String imageUploadPath;
    // POST - create user

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Intiating request for Controller save  user");
        UserDto createUser = this.userService.createUser(userDto);
        logger.info("Completed request for Controller save  user");
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);

    }

    // POST - update user

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        logger.info("Intiating request for Controller update  user");
        UserDto updateUser = this.userService.updateUser(userDto, userId);
        logger.info("Completed request for Controller update  user");
        System.out.println("Hello");
        return new ResponseEntity<UserDto>(updateUser, HttpStatus.CREATED);
    }

    // GET - getSingleUserById

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@Valid @PathVariable Integer userId) {
        logger.info("Intiating request for Controller get user");
        UserDto userById = this.userService.getUserById(userId);
        logger.info("Completed request for Controller get  user");
        return new ResponseEntity<UserDto>(userById, HttpStatus.OK);

    }

    // GET - getAllUser

    @GetMapping("/users")
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        logger.info("Intiating request for Controller getAll user");
        PageableResponse<UserDto> allUser = this.userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Completed request for Controller getAll user");
        return new ResponseEntity<PageableResponse<UserDto>>(allUser, HttpStatus.OK);

    }

    // GET - getSingleUserByEmailPassword

    @GetMapping("/user/{email}/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPassword(@Valid @PathVariable String email,
                                                             @PathVariable String password) {
        logger.info("Initiating request for Controller getSingle user");
        UserDto userDto = this.userService.getUserByEmailAndPassword(email, password);
        logger.info("Completed request for Controller getSingle user");
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

    }

    // GET - getSingleUserByEmail

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@Valid @PathVariable String email) {
        logger.info("Initiating request for Controller getSingle user");
        UserDto userDto = this.userService.getUserByEmail(email);
        logger.info("Completed request for Controller getSingle user");
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

    }

    // GET - searchAllUser
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchAllUserByKeyword(@Valid @PathVariable String keyword) {
        logger.info("Initiating request for Controller search user");
        List<UserDto> user = this.userService.searchUser(keyword);
        logger.info("Completed request for Controller search user");
        return new ResponseEntity<List<UserDto>>(user, HttpStatus.OK);

    }

    // DELETE - deleteUser

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ApiResposnse> deleteUser(@Valid @PathVariable Integer Id) {
        logger.info("Intiating request for Controller delete user");

        try {
            this.userService.deleteUser(Id);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ApiResposnse response = ApiResposnse.builder().messege(AppConstant.DELETE_USER).status(HttpStatus.OK).success(true).build();
        logger.info("Completed request for Controller delete user");

        return new ResponseEntity<ApiResposnse>(response, HttpStatus.OK);
    }

    // upload userImage
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image,
                                                         @PathVariable int userId) throws IOException {
        logger.info("Initiating request for Controller uploadImage");
        String imageName = this.fileService.uploadFile(image, imageUploadPath);

        UserDto user = userService.getUserById(userId);
        user.setImage(imageName);
        UserDto updateUser = userService.updateUser(user, userId);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName)
                .messege("Image is Successfully Saved").success(true).status(HttpStatus.CREATED).build();
        logger.info("Completed request for Controller uploadImage");
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    // serve user Image
    @GetMapping("/images/{userId}")
    public void serveUserImage(@PathVariable int userId, HttpServletResponse response) throws IOException {
        UserDto user = this.userService.getUserById(userId);
        logger.info("User Image Name:" + user.getImage());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImage());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }
}
