package com.example.tuwaiqlab11.Repository;

import com.example.tuwaiqlab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);
}
