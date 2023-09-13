package com.shoppingApp.usermanagementservice.service;


import com.shoppingApp.usermanagementservice.model.User;
import com.shoppingApp.usermanagementservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

//    public User login(String emailId,String password){
//        User user=userRepository.findByEmailIdAndPassword(emailId,password);
//        System.out.println(user);
//        return user;
//    }

    public User findByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);



    }
}
