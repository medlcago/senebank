package org.backend.senebank.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.dto.models.TransactionModel;
import org.backend.senebank.entities.Transaction;
import org.backend.senebank.services.Impl.AdminServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${api.v1.prefix}/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceImpl adminService;

    @GetMapping(value = "/transactions/")
    public ResponseEntity<?> findAllTransactions() {
        List<Transaction> transactions = adminService.findAllTransactions();
        return ResponseEntity.ok(TransactionModel.fromEntityList(transactions));

    }

    @GetMapping(value = "/transactions/user/{id}")
    public ResponseEntity<?> getTransactionsForUser(@PathVariable Long id) {
        List<Transaction> transactions = adminService.getTransactionsForUser(id);
        return ResponseEntity.ok(TransactionModel.fromEntityList(transactions));

    }

    @GetMapping(value = "/transactions/account/{id}")
    public ResponseEntity<?> getTransactionsForAccount(@PathVariable Long id) {
        List<Transaction> transactions = adminService.getTransactionsForAccount(id);
        return ResponseEntity.ok(TransactionModel.fromEntityList(transactions));
    }
}
