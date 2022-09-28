package com.example.questapp.services;

import com.example.questapp.dto.LikeCreateDto;

import com.example.questapp.entities.Like;
import com.example.questapp.repos.LikeRepository;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;
import responses.LikeResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository,UserService userService,PostService postService) {
        this.likeRepository = likeRepository;
        this.userService=userService;
        this.postService=postService;
    }


    public List<LikeResponse> getAllLikesWithParameter(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list= likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list= likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list= likeRepository.findByPostId(postId.get());

        } else
            list= likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateDto dto) {

        return null;
    }
}






