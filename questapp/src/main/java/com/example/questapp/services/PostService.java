package com.example.questapp.services;

import com.example.questapp.dto.PostCreateDto;
import com.example.questapp.dto.PostUpdateDto;
import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.PostRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import responses.LikeResponse;
import responses.PostResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class PostService {
    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    public PostService(PostRepository postRepository,UserService userService,LikeService likeService){
        this.postRepository=postRepository;
        this.userService=userService;
    }

    @Autowired
    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        }else
        list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes=likeService.getAllLikesWithParameter(Optional.ofNullable(null),Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());

        }


    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateDto newPostDto) {
        User user = userService.getOneUserById(newPostDto.getUserId());
        if (user==null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostDto.getId());
        toSave.setText(newPostDto.getText());
        toSave.setTitle(newPostDto.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateDto updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setTitle(updatePost.getTitle());
            toUpdate.setText(updatePost.getText());
            return postRepository.save(toUpdate);


        }
        return null;
    }


    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
