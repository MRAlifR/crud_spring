package com.mralifr.crud.controllers;

import com.mralifr.crud.models.requests.UserRequest;
import com.mralifr.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getDataUser("all"));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getDataUser(userId.toString()));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userRequest.setUserid(null);
        return new ResponseEntity<>(userService.setDataUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        userRequest.setUserid(userId);
        userService.setDataUser(userRequest);
        return ResponseEntity.ok(userService.setDataUser(userRequest));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.delDataUser(userId);
        return ResponseEntity.ok().build();
    }
}
