package org.backend.senebank.services;

import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account create(User user);

    Account getUserAccount(Long accountId, Long userId);

    Account findById(Long accountId);

    List<Account> findAllByUser(User user);

    List<Account> findAllByUser(Long userId);

    void delete(Account account);

    void delete(Long id);

    Account addBalanceToAccount(Account account, BigDecimal amount);

    Account subtractBalanceFromAccount(Account account, BigDecimal amount);

    Account closeUserAccount(User user, Long accountId);
}
