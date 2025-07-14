package com.securebank.api.repository;

import com.securebank.api.entity.Account;
import com.securebank.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserOrderByCreatedAtAsc(User user);

    List<Account> findByUserId(Long userId);

    List<Account> findByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<Account> findByIdAndUserId(Long id, Long userId);

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.user.id = :userId AND a.status = 'ACTIVE'")
    List<Account> findActiveAccountsByUserId(@Param("userId") Long userId);

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.transactions WHERE a.id = :accountId")
    Optional<Account> findByIdWithTransactions(@Param("accountId") Long accountId);

    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.user.id = :userId AND a.accountType != 'CREDIT' AND a.status = 'ACTIVE'")
    java.math.BigDecimal getTotalBalanceByUserId(@Param("userId") Long userId);
}
