package com.rescuepaw.controller;

import com.rescuepaw.dto.FeedbackDto;
import com.rescuepaw.entity.Feedback;
import com.rescuepaw.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // 获取所有用户反馈
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackList);
    }

    // 提交用户反馈
    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody FeedbackDto feedbackDto) {
        Feedback savedFeedback = feedbackService.submitFeedback(feedbackDto);
        return ResponseEntity.ok(savedFeedback);
    }

    // 更新反馈状态（管理员权限）
    @PutMapping("/{id}/status")
    public ResponseEntity<Feedback> updateFeedbackStatus(@PathVariable Long id, @RequestParam String status) {
        Feedback updatedFeedback = feedbackService.updateFeedbackStatus(id, status);
        return ResponseEntity.ok(updatedFeedback);
    }
}