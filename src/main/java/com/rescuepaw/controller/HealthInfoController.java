package com.rescuepaw.controller;

import com.rescuepaw.entity.HealthInfo;
import com.rescuepaw.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-info")
public class HealthInfoController {

    @Autowired
    private HealthInfoService healthInfoService;

    // 获取所有健康与护理信息
    @GetMapping
    public ResponseEntity<List<HealthInfo>> getAllHealthInfo() {
        List<HealthInfo> healthInfoList = healthInfoService.getAllHealthInfo();
        return ResponseEntity.ok(healthInfoList);
    }

    // 添加新的健康与护理信息（管理员权限）
    @PostMapping
    public ResponseEntity<HealthInfo> addHealthInfo(@RequestBody HealthInfo healthInfo) {
        HealthInfo savedHealthInfo = healthInfoService.addHealthInfo(healthInfo);
        return ResponseEntity.ok(savedHealthInfo);
    }

    // 更新健康与护理信息（管理员权限）
    @PutMapping("/{id}")
    public ResponseEntity<HealthInfo> updateHealthInfo(@PathVariable Long id, @RequestBody HealthInfo healthInfoDetails) {
        HealthInfo updatedHealthInfo = healthInfoService.updateHealthInfo(id, healthInfoDetails);
        return ResponseEntity.ok(updatedHealthInfo);
    }

    // 删除健康与护理信息（管理员权限）
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHealthInfo(@PathVariable Long id) {
        healthInfoService.deleteHealthInfo(id);
        return ResponseEntity.ok("健康与护理信息删除成功");
    }
}