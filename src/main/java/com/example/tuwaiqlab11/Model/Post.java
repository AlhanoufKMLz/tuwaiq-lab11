package com.example.tuwaiqlab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Category Id must not be null")
    private Integer categoryId;

    @NotNull(message = "User Id must not be null")
    private Integer userId;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Title must not be empty")
    @Size(min = 5, message = "Title must be more than 4 characters")
    private String title;

    @Column(columnDefinition = "varchar(500) not null")
    @NotEmpty(message = "Content must not be empty")
    private String content;

    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime publishedDate;
}
