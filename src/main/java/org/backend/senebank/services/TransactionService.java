package org.backend.senebank.services;

import org.backend.senebank.entities.Transaction;
import org.backend.senebank.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction create(Long senderId, Long receiverId, Long SenderAccountId, Long receiverAccountId, BigDecimal amount);

    Transaction findById(Long id);

    List<Transaction> findAllByUser(User user);

    List<Transaction> findAllByUser(Long id);

    List<Transaction> findAllByAccount(Long accountId);

    List<Transaction> findAll();

    void delete(Transaction transaction);

    void delete(Long id);
}
