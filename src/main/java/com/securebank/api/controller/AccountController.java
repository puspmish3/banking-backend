package com.securebank.api.controller;

import com.securebank.api.dto.AccountDTO;
import com.securebank.api.dto.TransactionDTO;
import com.securebank.api.entity.Account;
import com.securebank.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER')")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getUserAccounts() {
        try {
            List<AccountDTO> accounts = accountService.getUserAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId) {
        try {
            AccountDTO account = accountService.getAccountById(accountId);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequest request) {
        try {
            AccountDTO account = accountService.createAccount(request.getAccountType());
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAccountTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<TransactionDTO> transactions = accountService.getAccountTransactions(accountId, page, size);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<TransactionDTO> transactions = accountService.getAllUserTransactions(page, size);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Helper class for create account request
    public static class CreateAccountRequest {
        private Account.AccountType accountType;

        public CreateAccountRequest() {
        }

        public CreateAccountRequest(Account.AccountType accountType) {
            this.accountType = accountType;
        }

        public Account.AccountType getAccountType() {
            return accountType;
        }

        public void setAccountType(Account.AccountType accountType) {
            this.accountType = accountType;
        }
    }
}
