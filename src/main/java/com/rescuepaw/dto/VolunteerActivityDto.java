package com.rescuepaw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class VolunteerActivityDto {

    @NotBlank(message = "活动标题不能为空")
    private String title; // 活动标题

    @NotBlank(message = "活动描述不能为空")
    private String description; // 活动描述

    @NotNull(message = "活动开始时间不能为空")
    private Date startTime; // 活动开始时间

    @NotNull(message = "活动结束时间不能为空")
    private Date endTime; // 活动结束时间

    @NotBlank(message = "活动地点不能为空")
    private String location; // 活动地点

    // Getter 和 Setter 方法
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}