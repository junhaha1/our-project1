package com.adela.controller;

import com.adela.domain.userEntity;
import com.adela.dto.AddUserRequest;
import com.adela.dto.UpdateUserRequest;
import com.adela.dto.UserResponse;
import com.adela.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/board/user")
    public ResponseEntity<userEntity> addUser(@RequestBody AddUserRequest request){
        if (request.getRegDate() == null) {
            request.setRegDate(LocalDate.now());
        }
        userEntity savedUserEntity = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUserEntity);
    }

    @GetMapping("/board/user/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable("userId") String userId){
        userEntity userEntity = userService.findById(userId);

        return ResponseEntity.ok()
                .body(new UserResponse(userEntity));
    }

    @DeleteMapping("/board/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId){
        userService.delete(userId);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/board/user/{userId}")
    public ResponseEntity<userEntity> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserRequest request){
        userEntity updateUserEntity = userService.update(userId, request);

        return ResponseEntity.ok()
                .body(updateUserEntity);
    }

}
