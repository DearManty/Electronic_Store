package com.happytech.Electronic_Store.service;

import com.happytech.Electronic_Store.dto.PageableResponse;
import com.happytech.Electronic_Store.dto.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public interface UserService {
    // create

    UserDto createUser(UserDto userDto);
    // update

    UserDto updateUser(UserDto userDto, int userId);

    // delete

    void deleteUser(int userId) throws IOException;
    // get all users

    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
    // get single user by id

    UserDto getUserById(int userId);
    // get single user by email

    UserDto getUserByEmail(String userEmail);

    // get single user by password

    UserDto getUserByEmailAndPassword(String email, String password);
    // search user

    List<UserDto> searchUser(String keyword);
    // other user specific feature

}
