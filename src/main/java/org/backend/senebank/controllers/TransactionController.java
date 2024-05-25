package org.backend.senebank.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.dto.models.TransactionModel;
import org.backend.senebank.dto.request.TransactionRequest;
import org.backend.senebank.entities.Transaction;
import org.backend.senebank.entities.User;
import org.backend.senebank.security.user.UserDetailsImpl;
import org.backend.senebank.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("${api.v1.prefix}/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping(value = "/")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Long senderId = user.getId();
        Long receiverId = request.receiverId();
        Long senderAccountId = request.senderAccountId();
        Long receiverAccountId = request.receiverAccountId();
        BigDecimal amount = request.amount();
        Transaction transaction = transactionService.create(senderId, receiverId, senderAccountId, receiverAccountId, amount);
        return ResponseEntity.ok(TransactionModel.fromEntity(transaction));

    }

    @GetMapping(value = "/")
    public ResponseEntity<?> findAllByUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        List<Transaction> transactions = transactionService.findAllByUser(user);
        return ResponseEntity.ok(TransactionModel.fromEntityList(transactions));

    }
}
