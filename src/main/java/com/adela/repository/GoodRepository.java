package com.adela.repository;

import com.adela.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Long> {
}