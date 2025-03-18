package com.example.backend.domain.user.service;

import com.example.backend.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getCardImgUrlsByUserId(@RequestParam("userId") Long userId) {
        return userRepository.findCardImgUrlsByUserId(userId);
    }

}
