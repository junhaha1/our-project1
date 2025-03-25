package com.adela.dto;

import com.adela.domain.userEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    private String userId;
    private String name;
    private String pwd;
    private LocalDate regDate;

    public userEntity toEntity(){
        return userEntity.builder()
                .userId(userId)
                .name(name)
                .pwd(pwd)
                .regDate(regDate)
                .build();
    }
}
