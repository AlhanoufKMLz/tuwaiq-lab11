package com.example.tuwaiqlab11.Repository;

import com.example.tuwaiqlab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    @Query("SELECT c FROM Comment c WHERE c.postId = ?1")
    List<Comment> findAllCommentsForPost(Integer postId);

    @Query("SELECT c FROM Comment c WHERE c.userId = ?1")
    List<Comment> findAllCommentsForUser(Integer userId);
}
