package com.rescuepaw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ForumPostDto {

    @NotNull(message = "用户ID不能为空")
    private Long userId; // 发帖人ID

    @NotBlank(message = "帖子标题不能为空")
    private String title; // 帖子标题

    @NotBlank(message = "帖子内容不能为空")
    private String content; // 帖子内容

    // Getter 和 Setter 方法
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}