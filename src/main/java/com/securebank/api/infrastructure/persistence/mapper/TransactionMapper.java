package com.securebank.api.infrastructure.persistence.mapper;

import com.securebank.api.domain.model.Transaction;
import com.securebank.api.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final UserMapper userMapper;

    public Transaction toDomain(TransactionEntity entity) {
        if (entity == null) return null;
        return Transaction.builder()
                .id(entity.getId())
                .sender(userMapper.toDomain(entity.getSender()))
                .receiver(userMapper.toDomain(entity.getReceiver()))
                .amount(entity.getAmount())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public TransactionEntity toEntity(Transaction domain) {
        if (domain == null) return null;
        return TransactionEntity.builder()
                .id(domain.getId())
                .sender(userMapper.toEntity(domain.getSender()))
                .receiver(userMapper.toEntity(domain.getReceiver()))
                .amount(domain.getAmount())
                .timestamp(domain.getTimestamp())
                .build();
    }
}
