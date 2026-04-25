package com.securebank.api.application.service;

import com.securebank.api.application.dto.TransactionRequestDTO;
import com.securebank.api.application.dto.TransactionResponseDTO;
import com.securebank.api.domain.exception.BusinessException;
import com.securebank.api.domain.exception.NotFoundException;
import com.securebank.api.domain.model.Transaction;
import com.securebank.api.domain.model.User;
import com.securebank.api.domain.repository.TransactionRepository;
import com.securebank.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Transactional
    public TransactionResponseDTO transfer(Long senderId, TransactionRequestDTO dto) {
        if (senderId.equals(dto.getReceiverId())) {
            throw new BusinessException("Cannot transfer to the same account");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new NotFoundException("Sender not found"));
        
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new NotFoundException("Receiver not found"));

        if (sender.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new BusinessException("Insufficient balance");
        }

        // Deduct from sender, add to receiver
        sender.setBalance(sender.getBalance().subtract(dto.getAmount()));
        receiver.setBalance(receiver.getBalance().add(dto.getAmount()));

        userRepository.save(sender);
        userRepository.save(receiver);

        Transaction transaction = Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(dto.getAmount())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return mapToResponse(savedTransaction);
    }

    public List<TransactionResponseDTO> getUserTransactions(Long userId) {
        return transactionRepository.findBySenderIdOrReceiverId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO mapToResponse(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .senderId(transaction.getSender().getId())
                .receiverId(transaction.getReceiver().getId())
                .amount(transaction.getAmount())
                .timestamp(transaction.getTimestamp())
                .build();
    }
}
