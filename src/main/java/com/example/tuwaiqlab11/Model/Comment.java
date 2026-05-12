package com.example.tuwaiqlab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User Id must not be null")
    private Integer userId;

    @NotNull(message = "Post Id must not be null")
    private Integer postId;

    @Column(columnDefinition = "varchar(200) not null")
    @NotEmpty(message = "Content must not be empty")
    private String content;

    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime commentDate;
}
