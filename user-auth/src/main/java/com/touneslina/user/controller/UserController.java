package com.touneslina.user.controller;

import com.touneslina.user.authentication.ChangePasswordRequest;
import com.touneslina.user.entity.UserEntity;
import com.touneslina.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/find/{idUser}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long idUser){
        UserEntity user = userService.getUser(idUser);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<UserEntity> updateUser(
            @PathVariable long idUser,
            @RequestBody UserEntity updatedUser) {

        UserEntity user = userService.updateUser(updatedUser, idUser);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword (
            @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal UserEntity user){
        userService.changePassword(user.getId(), request);
        return ResponseEntity.ok("Password changed successfully");
    }


}
