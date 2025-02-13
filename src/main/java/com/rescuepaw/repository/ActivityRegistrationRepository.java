package com.rescuepaw.repository;

import com.rescuepaw.entity.ActivityRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRegistrationRepository extends JpaRepository<ActivityRegistration, Long> {

    // 根据用户ID查找报名记录
    List<ActivityRegistration> findByUserId(Long userId);

    // 根据活动ID查找报名记录
    List<ActivityRegistration> findByActivityId(Long activityId);
}