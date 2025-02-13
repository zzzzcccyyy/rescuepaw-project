package com.rescuepaw.repository;

import com.rescuepaw.entity.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {

    // 根据用户ID查找帖子
    List<ForumPost> findByUserId(Long userId);

    // 根据标题关键字查找帖子
    List<ForumPost> findByTitleContaining(String keyword);
}