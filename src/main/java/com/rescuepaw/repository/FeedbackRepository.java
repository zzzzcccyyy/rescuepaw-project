package com.rescuepaw.repository;

import com.rescuepaw.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // 根据用户ID查找反馈
    List<Feedback> findByUserId(Long userId);

    // 根据状态查找反馈
    List<Feedback> findByStatus(String status);
}