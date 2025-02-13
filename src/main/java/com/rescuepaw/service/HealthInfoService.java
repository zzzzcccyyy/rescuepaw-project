package com.rescuepaw.service;

import com.rescuepaw.entity.HealthInfo;
import com.rescuepaw.repository.HealthInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthInfoService {

    @Autowired
    private HealthInfoRepository healthInfoRepository;

    // 获取所有健康与护理信息
    public List<HealthInfo> getAllHealthInfo() {
        return healthInfoRepository.findAll();
    }

    // 添加新的健康与护理信息
    public HealthInfo addHealthInfo(HealthInfo healthInfo) {
        return healthInfoRepository.save(healthInfo);
    }

    // 更新健康与护理信息
    public HealthInfo updateHealthInfo(Long id, HealthInfo healthInfoDetails) {
        HealthInfo healthInfo = healthInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("健康信息未找到: " + id));
        healthInfo.setTitle(healthInfoDetails.getTitle());
        healthInfo.setContent(healthInfoDetails.getContent());
        return healthInfoRepository.save(healthInfo);
    }

    // 删除健康与护理信息
    public void deleteHealthInfo(Long id) {
        healthInfoRepository.deleteById(id);
    }
}