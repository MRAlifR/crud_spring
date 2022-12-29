package com.mralifr.crud.services;

import com.mralifr.crud.models.entities.UserEntity;
import com.mralifr.crud.models.mappers.UserMapper;
import com.mralifr.crud.models.requests.UserRequest;
import com.mralifr.crud.models.responses.UserResponse;
import com.mralifr.crud.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getDataUser(String allOrUserId) {
        if (allOrUserId.equalsIgnoreCase("all")) {
            return userRepo.findAll().stream().map(userMapper::toUserResponse).toList();
        } else if (isNumeric(allOrUserId)) {
            Long userId = Long.parseLong(allOrUserId);
            return userRepo.findById(userId).stream().map(userMapper::toUserResponse).toList();
        } else {
            return Collections.emptyList();
        }
    }

    public UserResponse setDataUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.toUserEntity(userRequest);

        // Edit existing user
        if (userRequest.getUserid() != null) {
            userEntity = userRepo.findById(userRequest.getUserid()).orElseThrow(EntityNotFoundException::new);
            userMapper.updateUserEntityFromRequest(userRequest, userEntity);
        }

        // Encrypt password if not null
        if (userRequest.getPassword() != null && !userRequest.getPassword().isBlank()) {
            String password = passwordEncoder.encode(userRequest.getPassword());
            userEntity.setPassword(password);
        }

        return userMapper.toUserResponse(userRepo.save(userEntity));
    }

    public void delDataUser(Long userId) {
        if (userRepo.existsById(userId)) {
            userRepo.deleteById(userId);
        }
    }

    private boolean isNumeric(String text) {
        try {
            Long userId = Long.parseLong(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
