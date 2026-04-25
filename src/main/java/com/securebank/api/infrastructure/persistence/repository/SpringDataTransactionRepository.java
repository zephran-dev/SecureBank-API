package com.securebank.api.infrastructure.persistence.repository;

import com.securebank.api.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {
    @Query("SELECT t FROM TransactionEntity t WHERE t.sender.id = :userId OR t.receiver.id = :userId")
    List<TransactionEntity> findBySenderIdOrReceiverId(@Param("userId") Long userId);
}
