package org.backend.senebank.repository;

import org.backend.senebank.entities.Transaction;
import org.backend.senebank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySenderOrReceiver(User sender, User receiver);

    List<Transaction> findAllBySenderIdOrReceiverId(Long senderId, Long receiverId);

    List<Transaction> findAllBySenderAccountIdOrReceiverAccountId(Long senderAccount_id, Long receiverAccount_id);
}
