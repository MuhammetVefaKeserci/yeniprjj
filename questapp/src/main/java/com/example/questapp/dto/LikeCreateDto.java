package com.example.questapp.dto;

import com.example.questapp.entities.Like;
import lombok.Data;

@Data
public class LikeCreateDto {
    Long id;
    Long  userId;
    Long postId;



}
