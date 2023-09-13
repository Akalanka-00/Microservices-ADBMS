package com.shoppingApp.usermanagementservice.controller;

import com.shoppingApp.usermanagementservice.model.User;
import com.shoppingApp.usermanagementservice.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
@RestController

@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav=new ModelAndView("login");
        mav.addObject("user",new User());
        return mav;
    }

//    @PostMapping("/login")
//    public String login( @RequestBody User user){
//        User authUser=loginService.login(user.getEmailId(),user.getPassword());
//
//        System.out.println(authUser);
//
//        if(Objects.nonNull(authUser)){
//            return "redirect :/";
//        }
//        else {
//            return "redirect:/login";
//        }
//
//    }

//    @PostMapping ("/login")
//    public ResponseEntity<String> login(@RequestBody User loginUser) {
//        User user = loginService.findByEmail(loginUser.getEmailId());
//
//        System.out.println("helllo");
//
//        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
//            // User authenticated successfully
//            return ResponseEntity.ok("Login successful");
//        } else {
//            // Authentication failed
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
//    }
}
