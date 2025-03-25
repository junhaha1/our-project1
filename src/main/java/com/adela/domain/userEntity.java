package com.adela.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class userEntity {

    //email
    @Id
    @Column(name = "userID", updatable = false)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "regDate")
    private LocalDate regDate;

    @Column(name = "deleteDate")
    private LocalDate deleteDate;

    @Builder
    public userEntity(String userId, String name, String pwd, LocalDate regDate){
        this.userId = userId;
        this.name = name;
        this.pwd = pwd;
        this.regDate = regDate;
    }

    public void update(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }
}
