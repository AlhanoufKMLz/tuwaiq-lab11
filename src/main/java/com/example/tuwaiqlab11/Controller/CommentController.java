package com.example.tuwaiqlab11.Controller;

import com.example.tuwaiqlab11.Api.ApiResponse;
import com.example.tuwaiqlab11.Model.Comment;
import com.example.tuwaiqlab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    //BASIC CRUD ENDPOINTS
    @GetMapping("/get")
    public ResponseEntity<?> getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully"));
    }


    //EXTRA ENDPOINTS
    @GetMapping("/get-post/{postId}")
    public ResponseEntity<?> getAllCommentsForPost(@PathVariable Integer postId){
        return ResponseEntity.status(200).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<?> getAllPostsByUser(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(commentService.getAllCommentsForUser(userId));
    }
}
