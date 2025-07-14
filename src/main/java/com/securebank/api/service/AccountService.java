package com.securebank.api.service;

import com.securebank.api.dto.AccountDTO;
import com.securebank.api.dto.TransactionDTO;
import com.securebank.api.entity.Account;
import com.securebank.api.entity.Transaction;
import com.securebank.api.entity.User;
import com.securebank.api.repository.AccountRepository;
import com.securebank.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AuthService authService;

    public List<AccountDTO> getUserAccounts() {
        User currentUser = authService.getCurrentUser();
        List<Account> accounts = accountRepository.findByUserIdOrderByCreatedAtDesc(currentUser.getId());

        return accounts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO getAccountById(Long accountId) {
        User currentUser = authService.getCurrentUser();
        Account account = accountRepository.findByIdAndUserId(accountId, currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return convertToDTO(account);
    }

    public AccountDTO createAccount(Account.AccountType accountType) {
        User currentUser = authService.getCurrentUser();

        Account account = new Account();
        account.setUser(currentUser);
        account.setAccountType(accountType);
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(Account.AccountStatus.ACTIVE);

        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    public List<TransactionDTO> getAccountTransactions(Long accountId, int page, int size) {
        User currentUser = authService.getCurrentUser();

        // Verify account belongs to current user
        accountRepository.findByIdAndUserId(accountId, currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Transaction> transactions = transactionRepository.findByAccountIdOrderByCreatedAtDesc(accountId, pageable);

        return transactions.getContent().stream()
                .map(this::convertToTransactionDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> getAllUserTransactions(int page, int size) {
        User currentUser = authService.getCurrentUser();
        List<Long> accountIds = accountRepository.findByUserId(currentUser.getId())
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());

        if (accountIds.isEmpty()) {
            return List.of();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Transaction> transactions = transactionRepository.findByAccountIdInOrderByCreatedAtDesc(accountIds,
                pageable);

        return transactions.getContent().stream()
                .map(this::convertToTransactionDTO)
                .collect(Collectors.toList());
    }

    private AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType().toString());
        dto.setBalance(account.getBalance());
        dto.setStatus(account.getStatus().toString());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setUpdatedAt(account.getUpdatedAt());
        return dto;
    }

    private TransactionDTO convertToTransactionDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAccountId(transaction.getAccount().getId());
        dto.setTransactionType(transaction.getTransactionType().toString());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        dto.setStatus(transaction.getStatus().toString());
        dto.setCreatedAt(transaction.getTransactionDate());
        return dto;
    }

    private String generateAccountNumber() {
        // Generate a unique 10-digit account number
        String timestamp = String.valueOf(System.currentTimeMillis());
        return "ACC" + timestamp.substring(timestamp.length() - 7);
    }
}
