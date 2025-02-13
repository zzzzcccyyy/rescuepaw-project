package com.rescuepaw.service;

import com.rescuepaw.dto.FeedbackDto;
import com.rescuepaw.entity.Feedback;
import com.rescuepaw.entity.User;
import com.rescuepaw.repository.FeedbackRepository;
import com.rescuepaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    // 获取所有用户反馈
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // 提交用户反馈
    public Feedback submitFeedback(FeedbackDto feedbackDto) {
        User user = userRepository.findById(feedbackDto.getUserId())
                .orElseThrow(() -> new RuntimeException("用户未找到: " + feedbackDto.getUserId()));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setContent(feedbackDto.getContent());
        feedback.setStatus("PENDING"); // 默认状态为待处理
        return feedbackRepository.save(feedback);
    }

    // 更新反馈状态
    public Feedback updateFeedbackStatus(Long id, String status) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("反馈未找到: " + id));
        feedback.setStatus(status);
        return feedbackRepository.save(feedback);
    }
}