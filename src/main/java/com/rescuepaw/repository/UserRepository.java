package com.rescuepaw.repository;

import com.rescuepaw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 定义根据角色查找用户的方法
    List<User> findByRole(String role);

    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    // 根据邮箱查找用户
    Optional<User> findByEmail(String email);

    // 检查用户名是否已存在
    boolean existsByUsername(String username);

    // 检查邮箱是否已存在
    boolean existsByEmail(String email);
}