package com.adela.repository;

import com.adela.domain.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<userEntity, String> {
}
