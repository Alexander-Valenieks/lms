package com.backend.lms.service;

//        import javax.annotation.Resource;
//        import java.util.Optional;

import com.backend.lms.dto.LoginDTO;
import com.backend.lms.dto.UserDTO;
import com.backend.lms.entity.User;
import com.backend.lms.payload.LoginMessage;
import com.backend.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String addUser(UserDTO userDTO) {

       User user = new User(

                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),

                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepository.save(user);

        return user.getName();
    }
    UserDTO userDTO;

    @Override
    public LoginMessage  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {

                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }


    }

}