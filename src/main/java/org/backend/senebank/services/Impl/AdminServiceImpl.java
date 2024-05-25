package org.backend.senebank.services.Impl;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.entities.Transaction;
import org.backend.senebank.services.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final TransactionServiceImpl transactionService;

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionService.findAll();
    }

    @Override
    public List<Transaction> getTransactionsForUser(Long userId) {
        return transactionService.findAllByUser(userId);
    }

    @Override
    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionService.findAllByAccount(accountId);
    }
}
