package com.touneslina.user.service;


import com.touneslina.user.authentication.ChangePasswordRequest;
import com.touneslina.user.entity.UserEntity;
import com.touneslina.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity getUser(long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity updateUser(UserEntity updatedUser, long userId){
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user!=null){
            user.setEmail(updatedUser.getEmail());
            user.setNom(updatedUser.getNom());
            return  user;
        }
        return null;
    }

    public void changePassword(long userId, ChangePasswordRequest request){
       UserEntity user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("User not found"));
       if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
           throw new RuntimeException("Old password is incorrect");
       }
       user.setMotDePasse(passwordEncoder.encode(request.getNewPassword()));
       userRepository.save(user);
    }
}
