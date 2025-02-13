package com.rescuepaw.repository;

import com.rescuepaw.entity.VolunteerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VolunteerActivityRepository extends JpaRepository<VolunteerActivity, Long> {

    // 根据标题查找活动
    List<VolunteerActivity> findByTitleContaining(String title);

    // 根据活动地点查找活动
    List<VolunteerActivity> findByLocation(String location);

    // 根据活动开始时间范围查找活动
    List<VolunteerActivity> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // 根据活动标题精确查找活动
    List<VolunteerActivity> findByTitle(String title);

    // 根据活动结束时间范围查找活动，修正参数类型为 LocalDateTime
    List<VolunteerActivity> findByEndTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // 根据活动状态查找活动（假设状态字段为字符串）
    List<VolunteerActivity> findByStatus(String status);
}