package org.example.Config;

import org.example.Entities.Transaction;
import org.example.Entities.User;
import org.example.Entities.Wallet;
import org.example.Services.IStorageService;
import org.example.Services.MapStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Bean
    public IStorageService<User> userStorage() {
        return new MapStorageService<>();
    }

    @Bean
    public IStorageService<Wallet> walletStorage() {
        return new MapStorageService<>();
    }

    @Bean
    public IStorageService<Transaction> transactionStorage() {
        return new MapStorageService<>();
    }
}
