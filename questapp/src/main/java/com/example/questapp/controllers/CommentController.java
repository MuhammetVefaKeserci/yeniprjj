package com.example.questapp.controllers;

import com.example.questapp.dto.CommentCreateDto;
import com.example.questapp.dto.CommentUpdateDto;
import com.example.questapp.entities.Comment;
import com.example.questapp.services.CommentService;
import com.example.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
private CommentService commentService;

public CommentController(CommentService commentService){this.commentService=commentService;}
    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
    return commentService.getAllCommentsWithParam(userId,postId);}

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable long commentId){
    return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateDto request){
    return commentService.createOneComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto request){
    return commentService.updateOneCommentById(commentId,request);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
    commentService.deleteOneCommentById(commentId);
    }



}
