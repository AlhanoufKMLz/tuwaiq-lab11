package com.example.tuwaiqlab11.Service;

import com.example.tuwaiqlab11.Api.ApiException;
import com.example.tuwaiqlab11.Model.Category;
import com.example.tuwaiqlab11.Model.Post;
import com.example.tuwaiqlab11.Model.User;
import com.example.tuwaiqlab11.Repository.CategoryRepository;
import com.example.tuwaiqlab11.Repository.PostRepository;
import com.example.tuwaiqlab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    //BASIC CRUD
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        Category category = categoryRepository.findCategoryById(post.getCategoryId());
        if(category == null) throw new ApiException("Category not found"); //check category

        User user = userRepository.findUserById(post.getUserId());
        if(user == null) throw new ApiException("User not found");//check user

        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post){
        Post oldPost = postRepository.findPostById(id);
        if(oldPost == null)
            throw new ApiException("Post not found");

        Category category = categoryRepository.findCategoryById(post.getCategoryId());
        if(category == null) throw new ApiException("Category not found"); //check category

        User user = userRepository.findUserById(post.getUserId());
        if(user == null) throw new ApiException("User not found");//check user

        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setUserId(post.getUserId());
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());

        postRepository.save(oldPost);
    }

    public void deletePost(Integer id){
        Post post = postRepository.findPostById(id);
        if(post == null) throw new ApiException("Post not found");

        postRepository.delete(post);
    }


    //EXTRA ENDPOINTS
    public Post findPostByTitle(String title){
        Post post = postRepository.findPostByTitle(title);

        if(post == null) throw new ApiException("Post not found");

        return post;
    }

    public List<Post> findPostByCategoryId(Integer categoryId){
        List<Post> categoryPosts = postRepository.findPostByCategoryId(categoryId);

        if(categoryPosts.isEmpty()) throw new ApiException("No posts found");

        return categoryPosts;
    }

    public List<Post> findPostByUserId(Integer userId){
        List<Post> userPosts = postRepository.findPostByUserId(userId);

        if(userPosts.isEmpty()) throw new ApiException("No posts found");

        return userPosts;
    }

    public List<Post> findPostsBeforeDate(LocalDateTime date){
        List<Post> posts = postRepository.findPostsBeforeDate(date);

        if(posts.isEmpty()) throw new ApiException("No posts found");

        return posts;
    }

    public List<Post> findPostsAfterDate(LocalDateTime date){
        List<Post> posts = postRepository.findPostsAfterDate(date);

        if(posts.isEmpty()) throw new ApiException("No posts found");

        return posts;
    }

    public List<Post> findPostsByYear(Integer year){
        if (year < 0 || year > LocalDateTime.now().getYear()) throw new ApiException("Invalid year");

        List<Post> posts = postRepository.findPostsByYear(year);

        if(posts.isEmpty()) throw new ApiException("No posts found");

        return posts;
    }

    public List<Post> findLatestPosts(Integer limit){
        if(limit <= 0) throw new ApiException("Limit must be positive number");
        List<Post> posts = postRepository.findLatestPosts(limit);

        if(posts.isEmpty()) throw new ApiException("No posts found");

        return posts;
    }

    public List<Post> searchPostByKeyword(String keyword){
        List<Post> posts = postRepository.searchPostByKeyword(keyword);

        if(posts.isEmpty()) throw new ApiException("No posts found");

        return posts;
    }
}
