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
}
