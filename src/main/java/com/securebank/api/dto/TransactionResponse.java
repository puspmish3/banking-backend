package com.securebank.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private Long id;
    private String transactionId;
    private BigDecimal amount;
    private String transactionType;
    private String description;
    private String category;
    private BigDecimal balanceAfter;
    private String status;
    private String referenceNumber;
    private String merchantName;
    private String location;
    private LocalDateTime transactionDate;
    private LocalDateTime processedDate;

    // Constructors
    public TransactionResponse() {
    }

    public TransactionResponse(Long id, String transactionId, BigDecimal amount,
            String transactionType, String description, String category,
            BigDecimal balanceAfter, String status, String referenceNumber,
            String merchantName, String location, LocalDateTime transactionDate,
            LocalDateTime processedDate) {
        this.id = id;
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.category = category;
        this.balanceAfter = balanceAfter;
        this.status = status;
        this.referenceNumber = referenceNumber;
        this.merchantName = merchantName;
        this.location = location;
        this.transactionDate = transactionDate;
        this.processedDate = processedDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(LocalDateTime processedDate) {
        this.processedDate = processedDate;
    }
}
