package com.rescuepaw.repository;

import com.rescuepaw.entity.AdoptionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionFormRepository extends JpaRepository<AdoptionForm, Long> {
    long countByStatus(AdoptionForm.AdoptionStatus status);

    // 根据用户ID查找领养申请
    List<AdoptionForm> findByUserId(Long userId);

    // 根据动物ID查找领养申请
    List<AdoptionForm> findByAnimalId(Long animalId);

    // 根据状态查找领养申请
    List<AdoptionForm> findByStatus(String status);
}