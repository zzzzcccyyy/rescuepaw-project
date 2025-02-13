package com.rescuepaw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class FeedbackDto {

    @NotNull(message = "用户ID不能为空")
    private Long userId; // 用户ID

    @NotBlank(message = "反馈内容不能为空")
    private String content; // 反馈内容

    // Getter 和 Setter 方法
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}