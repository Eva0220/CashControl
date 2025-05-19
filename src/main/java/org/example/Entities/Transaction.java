package org.example.Entities;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Transaction implements IEntity {
    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    private UUID id;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private UUID walletId;

    public Transaction(TransactionType type, BigDecimal amount, LocalDateTime timestamp, UUID walletId) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return "Transaction Id: " + id + "|" +
                "TransactionType: " + type + "|" +
                "Amount: " + amount + "|" +
                "Timestamp: " + timestamp + "|";
    }
}

