package org.example.Handlers.TransactionActions;

import org.example.Entities.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

@Component
public class TransactionInputHandler {
    private final Scanner scanner;

    public TransactionInputHandler(Scanner scanner) {
       this.scanner = scanner;
    }

    public UUID getId() {
        System.out.println("Введите id транзакции");
        String transactionId = scanner.nextLine();
        return UUID.fromString(transactionId);
    }

    public Transaction.TransactionType chooseTransactionType() {
        System.out.println("Выберите тип транзакции (1 - поступление / 2 - списание):");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 1 -> Transaction.TransactionType.DEPOSIT;
            case 2 -> Transaction.TransactionType.WITHDRAWAL;
            default -> null;
        };
    }

    public BigDecimal getAmount() {
        BigDecimal amount = null;
        while (amount == null) {
            System.out.println("Введите сумму транзакции:");
            String answer = scanner.nextLine();
            amount = new BigDecimal(answer);
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Сумма не может быть отрицательной.");
                amount = null;
            }
        }
        return amount;
    }
}
