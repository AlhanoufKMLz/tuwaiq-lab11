package com.example.tuwaiqlab11.Repository;

import com.example.tuwaiqlab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    Post findPostByTitle(String title);

    List<Post> findPostByCategoryId(Integer categoryId);

    List<Post> findPostByUserId(Integer userId);

    @Query("SELECT p FROM Post p WHERE p.publishedDate < ?1")
    List<Post> findPostsBeforeDate(LocalDateTime date);

    @Query("SELECT p FROM Post p WHERE p.publishedDate >= ?1")
    List<Post> findPostsAfterDate(LocalDateTime date);

    @Query("select p from Post p where year(p.publishedDate) = ?1")
    List<Post> findPostsByYear(Integer year);

    @Query("SELECT p FROM Post p ORDER BY p.publishedDate DESC limit ?1")
    List<Post> findLatestPosts(Integer limit);

    @Query("select p from Post p where p.title like %:keyword% or p.content like %?1%")
    List<Post> searchPostByKeyword(String keyword);
}
