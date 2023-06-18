package com.backend.lms.service;




import com.backend.lms.dto.UserDTO;
import com.backend.lms.dto.LoginDTO;
import com.backend.lms.payload.LoginMessage;

public interface UserService {
    String addUser(UserDTO userDTO);

    LoginMessage loginUser(LoginDTO loginDTO);

}