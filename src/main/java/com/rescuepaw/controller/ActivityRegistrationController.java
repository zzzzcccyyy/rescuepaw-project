package com.rescuepaw.controller;

import com.rescuepaw.entity.ActivityRegistration;
import com.rescuepaw.service.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-registrations")
public class ActivityRegistrationController {

    @Autowired
    private ActivityRegistrationService activityRegistrationService;

    // 获取所有活动报名记录
    @GetMapping
    public ResponseEntity<List<ActivityRegistration>> getAllRegistrations() {
        List<ActivityRegistration> registrations = activityRegistrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    // 提交活动报名
    @PostMapping
    public ResponseEntity<ActivityRegistration> registerForActivity(
            @RequestParam Long userId,
            @RequestParam Long activityId) {
        ActivityRegistration registration = activityRegistrationService.registerForActivity(userId, activityId);
        return ResponseEntity.ok(registration);
    }

    // 取消活动报名
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelRegistration(@PathVariable Long id) {
        activityRegistrationService.cancelRegistration(id);
        return ResponseEntity.ok("活动报名取消成功");
    }
}