package com.example.questapp.repos;


import com.example.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LikeRepository extends JpaRepository<Like, Long> {







    List<Like> findByUserId(Long aLong);

    List<Like> findByPostId(Long aLong);

    List<Like> findByUserIdAndPostId(Long userId, Long psotId);
}
