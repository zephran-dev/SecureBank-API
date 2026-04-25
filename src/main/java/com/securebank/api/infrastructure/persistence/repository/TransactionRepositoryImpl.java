package com.securebank.api.infrastructure.persistence.repository;

import com.securebank.api.domain.model.Transaction;
import com.securebank.api.domain.repository.TransactionRepository;
import com.securebank.api.infrastructure.persistence.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final SpringDataTransactionRepository springDataTransactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction save(Transaction transaction) {
        var entity = transactionMapper.toEntity(transaction);
        var saved = springDataTransactionRepository.save(entity);
        return transactionMapper.toDomain(saved);
    }

    @Override
    public List<Transaction> findBySenderIdOrReceiverId(Long userId) {
        return springDataTransactionRepository.findBySenderIdOrReceiverId(userId)
                .stream()
                .map(transactionMapper::toDomain)
                .collect(Collectors.toList());
    }
}
