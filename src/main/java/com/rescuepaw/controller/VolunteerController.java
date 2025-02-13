package com.rescuepaw.controller;

import com.rescuepaw.entity.VolunteerActivity;
import com.rescuepaw.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // 允许来自 http://localhost:8081 的跨域请求
@RequestMapping("/api/volunteer/register")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    // 获取所有志愿者活动
    @GetMapping
    public ResponseEntity<List<VolunteerActivity>> getAllActivities() {
        List<VolunteerActivity> activities = volunteerService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    // 志愿者注册
    @PostMapping("/api/volunteer/register")
    public ResponseEntity<VolunteerActivity> addActivity(@RequestBody VolunteerActivity activity) {
        VolunteerActivity savedActivity = volunteerService.addActivity(activity);
        return ResponseEntity.ok(savedActivity);
    }

    // 根据ID获取志愿者活动
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerActivity> getActivityById(@PathVariable Long id) {
        try {
            VolunteerActivity activity = volunteerService.getActivityById(id);
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据标题搜索志愿者活动
    @GetMapping("/search/title")
    public ResponseEntity<List<VolunteerActivity>> searchActivitiesByTitle(@RequestParam String title) {
        List<VolunteerActivity> activities = volunteerService.searchActivitiesByTitle(title);
        return ResponseEntity.ok(activities);
    }

    // 根据地点搜索志愿者活动
    @GetMapping("/search/location")
    public ResponseEntity<List<VolunteerActivity>> searchActivitiesByLocation(@RequestParam String location) {
        List<VolunteerActivity> activities = volunteerService.searchActivitiesByLocation(location);
        return ResponseEntity.ok(activities);
    }

    // 根据时间范围搜索志愿者活动
    @GetMapping("/search/time-range")
    public ResponseEntity<List<VolunteerActivity>> searchActivitiesByTimeRange(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        List<VolunteerActivity> activities = volunteerService.searchActivitiesByTimeRange(startTime, endTime);
        return ResponseEntity.ok(activities);
    }

    // 删除志愿者活动
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        volunteerService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}