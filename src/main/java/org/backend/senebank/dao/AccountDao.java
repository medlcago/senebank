package org.backend.senebank.dao;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.User;
import org.backend.senebank.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountDao {
    private final AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account create(User user) {
        Account account = Account.builder()
                .user(user)
                .build();
        return save(account);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> findAllByUser(User user) {
        return accountRepository.findAllByUser(user);
    }

    public List<Account> findAllByUser(Long id) {
        return accountRepository.findAllByUserId(id);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
