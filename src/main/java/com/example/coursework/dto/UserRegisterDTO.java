package com.example.coursework.dto;

import com.example.coursework.valid.Password;
import com.example.coursework.valid.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    @Email(message  = "Неправильний формат")
    @NotBlank(message = "Введіть електронну пошту")
    private String email;

    @NotBlank(message = "Введіть прізвище")
    private String firstname;

    @NotBlank(message = "Введіть по-батькові")
    private String patronymic;

    @NotBlank(message = "Введіть ім'я")
    private String lastname;

    @NotBlank(message = "Введіть номер телефону")
    @Length(min = 10, max = 10, message = "Номер телефону повинен містити 10 символів")
    @PhoneNumber(message = "Номер телефону повинен мати формат 0ХХХХХХХХХ, де Х - цифри")
    private String phoneNumber;

    @NotBlank(message = "Введіть пароль")
    @Password(message = "Пароль повинен містити латинські літери та цифри")
    @Length(min = 8,max = 15, message = "Пароль повинен мати довжину від 8 до 15 символів включно")
    private String password;
}