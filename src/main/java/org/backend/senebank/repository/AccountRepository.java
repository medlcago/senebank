package org.backend.senebank.repository;

import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser(User user);

    List<Account> findAllByUserId(Long id);
}
