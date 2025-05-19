package org.example.Entities;

import lombok.Data;
import java.math.BigDecimal;

import java.util.UUID;

@Data
public class Wallet implements IEntity {
    public enum Currency {
        EUR,
        RUB,
        USD
    }

    private UUID id;
    private String name;
    private BigDecimal balance;
    private Currency currency;
    private UUID userId;

    public Wallet(String name, BigDecimal balance, Currency currency, UUID userId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.balance = balance;
        this.currency = currency;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Wallet Id: " + id + "|" +
                "Name: " + name + "|" +
                "Balance: " + balance + "|" +
                "Currency: " + currency.name() + "|";
    }
}

