package org.backend.senebank.services.Impl;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.dao.AccountDao;
import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.User;
import org.backend.senebank.exceptions.AccessDeniedException;
import org.backend.senebank.services.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    @Override
    @Transactional
    public Account create(User user) {
        return accountDao.create(user);
    }

    @Override
    public Account getUserAccount(Long accountId, Long userId) {
        Account account = accountDao.findById(accountId);
        if (account == null || !account.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Access denied");
        }
        return account;
    }

    @Override
    public Account findById(Long accountId) {
        return accountDao.findById(accountId);
    }

    @Override
    public List<Account> findAllByUser(User user) {
        return accountDao.findAllByUser(user);
    }

    @Override
    public List<Account> findAllByUser(Long userId) {
        return accountDao.findAllByUser(userId);
    }

    @Override
    public void delete(Account account) {
        accountDao.delete(account);
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    @Transactional
    public Account addBalanceToAccount(Account account, BigDecimal amount) {
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        return accountDao.save(account);
    }

    @Override
    @Transactional
    public Account subtractBalanceFromAccount(Account account, BigDecimal amount) {
        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        return accountDao.save(account);
    }

    @Override
    @Transactional
    public Account closeUserAccount(User user, Long accountId) {
        Account account = accountDao.findById(accountId);
        if (account == null || !account.getUser().equals(user)) {
            throw new AccessDeniedException("Access denied");
        }
        account.setOpen(false);
        return accountDao.save(account);
    }
}
