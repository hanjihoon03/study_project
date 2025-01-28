package com.example.study_project.controller;

import com.example.study_project.dto.UserDto;
import com.example.study_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/search/{name}")
    public List<UserDto> searchName(@PathVariable String username) {
        return userService.searchName(username);
    }
}
