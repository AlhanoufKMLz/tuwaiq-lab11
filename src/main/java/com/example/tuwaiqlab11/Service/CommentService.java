package com.example.tuwaiqlab11.Service;

import com.example.tuwaiqlab11.Api.ApiException;
import com.example.tuwaiqlab11.Model.Comment;
import com.example.tuwaiqlab11.Model.Post;
import com.example.tuwaiqlab11.Model.User;
import com.example.tuwaiqlab11.Repository.CommentRepository;
import com.example.tuwaiqlab11.Repository.PostRepository;
import com.example.tuwaiqlab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    //BASIC CRUD
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        User user = userRepository.findUserById(comment.getUserId());
        if(user == null) throw new ApiException("User not found");//check user

        Post post = postRepository.findPostById(comment.getPostId());
        if(post == null) throw new ApiException("Post not found"); //check post

        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment){
        Comment oldComment = commentRepository.findCommentById(id);
        if(oldComment == null)
            throw new ApiException("Comment not found");

        User user = userRepository.findUserById(comment.getUserId());
        if(user == null) throw new ApiException("User not found");//check user

        Post post = postRepository.findPostById(comment.getPostId());
        if(post == null) throw new ApiException("Post not found"); //check post

        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());
        oldComment.setContent(comment.getContent());

        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.findCommentById(id);
        if(comment == null) throw new ApiException("Comment not found");

        commentRepository.delete(comment);
    }


    //EXTRA ENDPOINTS
    public List<Comment> getAllCommentsForPost(Integer postId){
        List<Comment> postComments = commentRepository.findAllCommentsForPost(postId);

        if(postComments.isEmpty()) throw new ApiException("No comments found");

        return postComments;
    }

    public List<Comment> getAllCommentsForUser(Integer userId){
        List<Comment> userComments = commentRepository.findAllCommentsForUser(userId);

        if(userComments.isEmpty()) throw new ApiException("No comments found");

        return userComments;
    }
}
