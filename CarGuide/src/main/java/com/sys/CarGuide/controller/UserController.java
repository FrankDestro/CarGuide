package com.sys.CarGuide.controller;

import com.sys.CarGuide.models.dto.UserDTO;
import com.sys.CarGuide.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> loggedUserData() {
        UserDTO dto = userService.loggedUserData();
        return ResponseEntity.ok(dto);
    }

}
