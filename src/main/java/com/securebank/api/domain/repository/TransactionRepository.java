package com.securebank.api.domain.repository;

import com.securebank.api.domain.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findBySenderIdOrReceiverId(Long userId);
}
