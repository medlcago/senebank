package org.backend.senebank.services.Impl;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.dao.TransactionDao;
import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.Transaction;
import org.backend.senebank.entities.User;
import org.backend.senebank.exceptions.EntityNotFoundException;
import org.backend.senebank.exceptions.InsufficientFundsException;
import org.backend.senebank.services.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final UserServiceImpl userService;
    private final AccountServiceImpl accountService;

    @Override
    @Transactional
    public Transaction create(Long senderId, Long receiverId, Long SenderAccountId, Long receiverAccountId, BigDecimal amount) {
        Optional<User> sender = userService.findById(senderId);
        Optional<User> receiver = userService.findById(receiverId);
        Account senderAccount = accountService.getUserAccount(SenderAccountId, senderId);
        Account receiverAccount = accountService.findById(receiverAccountId);
        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new EntityNotFoundException("Отправитель или получатель не найден");
        }
        if (senderAccount == null) {
            throw new EntityNotFoundException("Счет отправителя не найден");
        }
        if (receiverAccount == null) {
            throw new EntityNotFoundException("Счет получателя не найден");
        }
        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("На вашем счете недостаточно средств");
        }
        accountService.subtractBalanceFromAccount(senderAccount, amount);
        accountService.addBalanceToAccount(receiverAccount, amount);
        return transactionDao.create(sender.get(), receiver.get(), senderAccount, receiverAccount, amount);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDao.findById(id);
    }

    @Override
    public List<Transaction> findAllByUser(User user) {
        return transactionDao.findAllByUser(user);
    }

    @Override
    public List<Transaction> findAllByUser(Long id) {
        return transactionDao.findAllByUser(id);
    }

    @Override
    public List<Transaction> findAllByAccount(Long accountId) {
        return transactionDao.findAllByAccount(accountId);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }


    @Override
    public void delete(Transaction transaction) {
        transactionDao.delete(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionDao.delete(id);
    }
}
