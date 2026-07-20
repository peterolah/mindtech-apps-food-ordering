package com.example.foodorder.web;

import com.example.foodorder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth/register")
    public void register(@RequestBody UserRequest userRequest){
        userService.register(userRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/auth/who-am-i")
    public UserDto whoAmI(){
        return userService.getLoggedInUser();
    }


}



