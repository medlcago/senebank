package org.backend.senebank.dao;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.Transaction;
import org.backend.senebank.entities.User;
import org.backend.senebank.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionDao {
    private final TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction create(User sender, User receiver, Account senderAccount, Account receiverAccount, BigDecimal amount) {
        Transaction transaction = Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .senderAccount(senderAccount)
                .receiverAccount(receiverAccount)
                .amount(amount)
                .build();
        return save(transaction);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public List<Transaction> findAllByUser(User user) {
        return transactionRepository.findAllBySenderOrReceiver(user, user);
    }

    public List<Transaction> findAllByUser(Long id) {
        return transactionRepository.findAllBySenderIdOrReceiverId(id, id);
    }

    public List<Transaction> findAllByAccount(Long accountId) {
        return transactionRepository.findAllBySenderAccountIdOrReceiverAccountId(accountId, accountId);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
