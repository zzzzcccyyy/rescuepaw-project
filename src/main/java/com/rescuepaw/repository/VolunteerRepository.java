package com.rescuepaw.repository;

import com.rescuepaw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<User, Long> {

    // 根据角色查找志愿者
    List<User> findByRole(String role);
}