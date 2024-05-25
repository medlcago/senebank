package org.backend.senebank.services;

import org.backend.senebank.entities.Transaction;

import java.util.List;

public interface AdminService {
    List<Transaction> findAllTransactions();

    List<Transaction> getTransactionsForUser(Long userId);

    List<Transaction> getTransactionsForAccount(Long accountId);
}
