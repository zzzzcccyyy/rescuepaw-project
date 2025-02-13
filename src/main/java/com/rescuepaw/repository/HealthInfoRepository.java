package com.rescuepaw.repository;

import com.rescuepaw.entity.HealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInfoRepository extends JpaRepository<HealthInfo, Long> {
}