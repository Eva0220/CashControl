package org.example.Services;
import org.example.Entities.User;
import org.example.Exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final IStorageService<User> userStorage;

    public UserService(IStorageService<User> userStorage) {
        this.userStorage = userStorage;
    }

    public UUID createUser(String fullName, int age, String passportData, String email) {
        User user = new User(fullName, age, passportData, email);
        userStorage.save(user);
        System.out.println("Пользователь создан:\n" + "Id: " + user.getId());
        return user.getId();
    }

    public void updateUser(UUID userId, String fullName, Integer age, String passportData, String email) {
        User user = userStorage.findById(userId);
        if (user != null) {
            Optional.ofNullable(fullName).ifPresent(user::setFullName);
            Optional.ofNullable(age).ifPresent(user::setAge);
            Optional.ofNullable(passportData).ifPresent(user::setPassportData);
            Optional.ofNullable(email).ifPresent(user::setEmail);
            System.out.println("Данные обновлены");
        } else {
            throw new UserNotFoundException("Пользователь с Id " + userId + " не найден");
        }
    }

    public void deleteUser(UUID userId) {
        User user = userStorage.findById(userId);
        if (user != null) {
            userStorage.delete(userId);
            System.out.println("Пользователь удален");
        } else {
            throw new UserNotFoundException("Пользователь с Id " + userId + " не найден");
        }
    }

    public User readUser(UUID userId) {
        User user = userStorage.findById(userId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("Пользователь с id " + userId + " не найден");
        }
    }
}
