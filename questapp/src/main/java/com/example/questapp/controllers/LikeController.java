package com.example.questapp.controllers;

import com.example.questapp.dto.LikeCreateDto;

import com.example.questapp.entities.Like;
import com.example.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;
    public LikeController(LikeService likeService){
        this.likeService=likeService;
    }
    @GetMapping
    public List<LikeCreateDto> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId)
    {return likeService.getAllLikesWithParameter(userId,postId);
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateDto dto )
    {
        return likeService.createOneLike(dto);
    }
}
