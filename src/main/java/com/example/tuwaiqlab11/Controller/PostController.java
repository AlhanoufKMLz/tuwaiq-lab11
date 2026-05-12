package com.example.tuwaiqlab11.Controller;

import com.example.tuwaiqlab11.Api.ApiResponse;
import com.example.tuwaiqlab11.Model.Post;
import com.example.tuwaiqlab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    //BASIC CRUD ENDPOINTS
    @GetMapping("/get")
    public ResponseEntity<?> getAllPosts(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully"));
    }


    //EXTRA ENDPOINTS
    @GetMapping("/get-title/{title}")
    public ResponseEntity<?> findPostByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.findPostByTitle(title));
    }

    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity<?> findPostByCategoryId(@PathVariable Integer categoryId){
        return ResponseEntity.status(200).body(postService.findPostByCategoryId(categoryId));
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<?> findAllPostsByUser(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(postService.findPostByUserId(userId));
    }

    @GetMapping("/get-before")
    public ResponseEntity<?> getPostsBeforeDate(@RequestParam LocalDateTime date){
        return ResponseEntity.status(200).body(postService.findPostsBeforeDate(date));
    }

    @GetMapping("/get-after")
    public ResponseEntity<?> getPostsAfterDate(@RequestParam LocalDateTime date){
        return ResponseEntity.status(200).body(postService.findPostsAfterDate(date));
    }

    @GetMapping("/get-year/{year}")
    public ResponseEntity<?> findPostsByYear(@PathVariable Integer year){
        return ResponseEntity.status(200).body(postService.findPostsByYear(year));
    }

    @GetMapping("/get-latest/{limit}")
    public ResponseEntity<?> findLatestPosts(@PathVariable Integer limit){
        return ResponseEntity.status(200).body(postService.findLatestPosts(limit));
    }

    @GetMapping("/get-search/{keyword}")
    public ResponseEntity<?> searchPostByKeyword(@PathVariable String keyword){
        return ResponseEntity.status(200).body(postService.searchPostByKeyword(keyword));
    }
}
