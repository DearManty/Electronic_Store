package com.happytech.Electronic_Store.service;

import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.UserDto;
import com.happytech.Electronic_Store.entity.User;
import com.happytech.Electronic_Store.exception.ResourceNotFoundException;
import com.happytech.Electronic_Store.helper.AppConstant;
import com.happytech.Electronic_Store.helper.Helper;
import com.happytech.Electronic_Store.repository.UserRepository;
import net.bytebuddy.TypeCache;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${user.profile.image.paths}")
    private String imagePath;
    //Create
    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("Intiating request for Service create  user");
        // generate uniqe id in string format
//		String userId = UUID.randomUUID().toString();
//		userDto.setUserid(userId);
        User USER = dtoToEntity(userDto);
        User saveUSER = this.userRepository.save(USER);
        UserDto newdTO = EntityToDto(saveUSER);
        logger.info("End request for Create   user");
        return newdTO;

    }

    //  update
    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        logger.info("Intiating request for update   user");
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(AppConstant.EXCEPTION + userId));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setImage(userDto.getImage());
        User updateduser = this.userRepository.save(user);
        UserDto updatedUserDto = EntityToDto(updateduser);
        logger.info("End request for update   user");
        return updatedUserDto;
    }




    //delete
    @Override
    public void deleteUser(int userId){
        logger.info("Intiating request for delete   user");
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION + userId));
        //delete user Profile Photo
        String fullPath=imagePath+user.getImage();
        Path path= Paths.get(fullPath);
        try {
            Files.delete(path);
        }catch(NoSuchFileException ex){
            logger.info("User image not found in folder");
            ex.printStackTrace();
        }

        catch (IOException e) {
            throw new RuntimeException(e);

        }
        this.userRepository.delete(user);
        logger.info("End request for delete   user");
    }

    //getAllUsers
    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        logger.info("Intiating request for getAll   user");

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pagable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = this.userRepository.findAll(pagable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        return response;
    }

    //getUserById
    @Override
    public UserDto getUserById(int userId) {
        logger.info("Intiating request for getsingle   user");
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION + userId));
        UserDto userDto = EntityToDto(user);
        logger.info("End request for getSingle   user");
        return userDto;
    }

    //getUserByEmail
    @Override
    public UserDto getUserByEmail(String userEmail) {
        logger.info("Intiating request for getSingle  user by email");
        User user = this.userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION + userEmail));
        UserDto UserDto = EntityToDto(user);
        logger.info("End request for getSingle user by email");
        return UserDto;
    }

    //getUserByKeyword
    @Override
    public List<UserDto> searchUser(String keyword) {
        logger.info("Intiating request for search   user");
        List<User> users = this.userRepository.findByName(keyword);
        List<UserDto> userDtos = users.stream().map(user -> EntityToDto(user)).collect(Collectors.toList());
        logger.info("End request for search  user");
        return userDtos;
    }

    private User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    //	 User user = User.builder().userid(userDto.getUserid())
//	                 .name(userDto.getName())
//	                 .email(userDto.getEmail())
//	                 .password(userDto.getPassword())
//	                 .gender(userDto.getGender())
//	                 .about(userDto.getAbout())
//	                 .image(userDto.getImage())
//	                 .build();
//
//	return user;
//}
    private UserDto EntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
//		 UserDto userDto = UserDto.builder().userid(user.getUserid())
//		                 .name(user.getName())
//		                 .email(user.getEmail())
//		                 .password(user.getPassword())
//		                 .gender(user.getGender())
//		                 .about(user.getAbout())
//		                 .image(user.getImage())
//		                 .build();
//
//		return userDto;
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        logger.info("Intiating request for search   user");
        User user = this.userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION + email + password));
        UserDto userDto = EntityToDto(user);
        logger.info("End request for search   user");
        return userDto;
    }
}
