package org.example.Entities;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class User implements IEntity {
    private UUID id;
    @NotBlank(message = "ФИО не должно быть пустым")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё]+([\\sA-Za-zА-Яа-яЁё]+)*$", message = "ФИО должно содержать только буквы (русские или английские) и пробелы")
    private String fullName;
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    private int age;
    @Pattern(regexp = "^[0-9]{10}$", message = "Введите ровно 10 цифр")
    private String passportData;
    @Email(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Неверный формат email")
    private String email;

    public User(String fullName, int age, String passportData, String email) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.age = age;
        this.passportData = passportData;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User Id: " + id + "\n" +
                "Name: " + fullName + "\n" +
                "Age: " + age + "\n" +
                "PassportData: " + passportData + "\n" +
                "Email: " + email + "\n";
    }
}

