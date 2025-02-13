package com.rescuepaw.controller;

import com.rescuepaw.service.AdoptionService;
import com.rescuepaw.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private AdoptionService adoptionService;

    @Autowired
    private VolunteerService volunteerService;

    // 获取领养成功率和活动参与人数
    @GetMapping
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("adoptionSuccessRate", adoptionService.calculateSuccessRate());
        stats.put("volunteerActivityCount", volunteerService.getActivityCount());
        return ResponseEntity.ok(stats);
    }
}