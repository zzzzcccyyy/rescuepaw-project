package com.rescuepaw.service;

import com.rescuepaw.entity.ActivityRegistration;
import com.rescuepaw.entity.User;
import com.rescuepaw.entity.VolunteerActivity;
import com.rescuepaw.repository.ActivityRegistrationRepository;
import com.rescuepaw.repository.UserRepository;
import com.rescuepaw.repository.VolunteerActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityRegistrationService {

    @Autowired
    private ActivityRegistrationRepository activityRegistrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VolunteerActivityRepository volunteerActivityRepository;

    // 获取所有活动报名记录
    public List<ActivityRegistration> getAllRegistrations() {
        return activityRegistrationRepository.findAll();
    }

    // 提交活动报名
    public ActivityRegistration registerForActivity(Long userId, Long activityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户未找到: " + userId));
        VolunteerActivity activity = volunteerActivityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("活动未找到: " + activityId));

        ActivityRegistration registration = new ActivityRegistration();
        registration.setUser(user);
        registration.setActivity(activity);
        return activityRegistrationRepository.save(registration);
    }

    // 取消活动报名
    public void cancelRegistration(Long id) {
        activityRegistrationRepository.deleteById(id);
    }
}