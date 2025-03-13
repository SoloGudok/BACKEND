package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long>,  ExpenditureRepositoryCustom {
}
