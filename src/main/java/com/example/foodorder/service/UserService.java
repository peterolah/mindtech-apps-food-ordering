package com.example.foodorder.service;

import com.example.foodorder.data.User;
import com.example.foodorder.data.UserRepository;
import com.example.foodorder.data.UserRole;
import com.example.foodorder.web.UserDto;
import com.example.foodorder.web.UserRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(UserRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(UserRole.CUSTOMER.toString());
        return userRepository.save(user);
    }

    public UserDto getLoggedInUser() {
       return userRepository.getLoggedInUser()
               .map(user ->  new UserDto(user.getUsername(), user.getEmail(), user.getRole()))
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
