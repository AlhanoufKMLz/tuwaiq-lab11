package com.example.tuwaiqlab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be at least 3")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must only contain letters and spaces")
    private String name;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters, " +
                    "include an uppercase letter, " +
                    "a lowercase letter, " +
                    "a number, " +
                    "and a special character.")
    private String password;

    @Column(columnDefinition = "varchar(50) not null unique")
    @NotEmpty(message = "E-mail must not be empty")
    @Email(message = "Invalid email")
    private String email;

    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime registrationDate;
}
