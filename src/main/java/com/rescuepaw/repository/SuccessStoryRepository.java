package com.rescuepaw.repository;

import com.rescuepaw.entity.SuccessStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuccessStoryRepository extends JpaRepository<SuccessStory, Long> {
}