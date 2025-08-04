package com.touneslina.loan.repository;

import com.touneslina.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findAllByPropertyId(long idProperty);
}
