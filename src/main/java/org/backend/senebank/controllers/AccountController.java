package org.backend.senebank.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.dto.models.AccountModel;
import org.backend.senebank.entities.Account;
import org.backend.senebank.entities.User;
import org.backend.senebank.security.user.UserDetailsImpl;
import org.backend.senebank.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.v1.prefix}/accounts", produces = "application/json")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Account account = accountService.create(user);
        return ResponseEntity.status(201).body(AccountModel.fromEntity(account));

    }

    @GetMapping("/")
    public ResponseEntity<?> findAllByUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        List<Account> accounts = accountService.findAllByUser(user);
        return ResponseEntity.ok(AccountModel.fromEntityList(accounts));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        Long userId = userDetails.getUser().getId();
        Account account = accountService.getUserAccount(id, userId);
        return ResponseEntity.ok(AccountModel.fromEntity(account));

    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<?> closeAccount(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        User user = userDetails.getUser();
        Account account = accountService.closeUserAccount(user, id);
        return ResponseEntity.ok(AccountModel.fromEntity(account));

    }
}
