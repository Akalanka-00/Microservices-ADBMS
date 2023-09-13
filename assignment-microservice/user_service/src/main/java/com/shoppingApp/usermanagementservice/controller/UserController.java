package com.shoppingApp.usermanagementservice.controller;

import com.shoppingApp.usermanagementservice.dto.ProductDto;
import com.shoppingApp.usermanagementservice.exception.ResourceNotFoundException;
import com.shoppingApp.usermanagementservice.model.User;
import com.shoppingApp.usermanagementservice.repo.UserRepository;
import com.shoppingApp.usermanagementservice.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/v1")

public class UserController {


    private final RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @GetMapping("/users")

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User employeeDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setEmailId(employeeDetails.getEmailId());
        user.setLastName(employeeDetails.getLastName());
        user.setFirstName(employeeDetails.getFirstName());
        final User updatedEmployee = userRepository.save(user);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PostMapping ("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser,String emailId) {


        User user = userRepository.findByEmailId(loginUser.getEmailId());




        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            // User authenticated successfully
            System.out.println("Login successful");
            return ResponseEntity.ok("Login successful");
        } else {
            // Authentication failed
            System.out.println("Login failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }


//    public User findByUsername(String emailId) {
//
//        return userRepository.findByUsername(emailId);
//    }

    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/orders")
    public String viewProductsByUserId() {


        // Replace "http://product-service" with the actual URL of your Product service
        String  products = restTemplate.getForObject("http://localhost:8080/api/product" , String.class);
        System.out.println(products);
        return "User " + " views products: " + products;
    }


    @GetMapping("/orders/{id}")
    public String viewProductsByUserId(@PathVariable Long id) {


        System.out.println("hey");

        // Replace "http://product-service" with the actual URL of your Product service
        String  products = restTemplate.getForObject("http://localhost:8080/api/product"+ id , String.class);
        System.out.println(products);
        return "User " + " views products: " + products;
    }


    @PostMapping("/placeorder")
    public String placeOrder() {

        System.out.println("sdfsdf");
        // Replace "http://product-service" with the actual URL of your Product service
        String  orders = restTemplate.getForObject("http://localhost:8081/api/order" , String.class);
        System.out.println(orders);
        return "User " + " views products: " + orders;
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user, UriComponentsBuilder builder) {
        // Send a POST request to the Order Service to create a new user
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(
                "http://localhost:8081/api/order",
                user,
                User.class);

        // Handle the response from the Order Service (e.g., check for success)
        // ...

        // Return a response, including the created user
        return ResponseEntity.created(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri()).body(user);
    }




}
