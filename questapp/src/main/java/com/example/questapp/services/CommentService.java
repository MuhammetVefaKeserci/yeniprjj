package com.example.questapp.services;

import com.example.questapp.dto.CommentCreateDto;
import com.example.questapp.dto.CommentUpdateDto;
import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.CommentRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;
    public CommentService(CommentRepository commentRepository,UserService userService,PostService postService){
        this.commentRepository=commentRepository;
        this.userService=userService;
        this.postService=postService;}


    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());

        }else
        return commentRepository.findAll() ;
    }

    public Comment getOneCommentById(long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateDto request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post !=null){
            Comment commentToSave = new Comment();
            commentToSave.setId(commentToSave.getId());
            commentToSave.setPost(commentToSave.getPost());
            commentToSave.setText(commentToSave.getText());
            commentToSave.setUser(commentToSave.getUser());
            return commentRepository.save(commentToSave);
        }else
        return null;

    }



    public Comment updateOneCommentById(Long commentId, CommentUpdateDto request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText()); //textini request den gelen text ile de??i??tirelim
            return commentRepository.save(commentToUpdate); //ve update edilmi?? son halini repomuza kaydedelim
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
