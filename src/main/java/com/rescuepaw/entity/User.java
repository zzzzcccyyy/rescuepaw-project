package com.rescuepaw.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements UserDetails { // 实现 UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username; // 用户名（唯一）

    @Column(name = "password", nullable = false)
    private String password; // 密码（加密存储）

    @Column(name = "email", nullable = false)
    private String email; // 邮箱

    @Column(name = "phone")
    private String phone; // 手机号

    @Column(name = "role", nullable = false)
    private String role; // 角色（ADMIN, USER, VOLUNTEER）

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // 注册时间

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt; // 更新时间

    // 构造函数
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // 在保存前自动设置创建时间和更新时间
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // 在更新时自动设置更新时间
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // 实现 UserDetails 接口的方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将用户的角色转换为 GrantedAuthority 对象
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    /**
     * 实现 UserDetails 接口的 getPassword 方法，返回用户的加密密码
     * @return 用户的加密密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 实现 UserDetails 接口的 getUsername 方法，返回用户名
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 账户是否未过期（默认返回 true）
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 账户是否未锁定（默认返回 true）
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 凭证是否未过期（默认返回 true）
    }

    @Override
    public boolean isEnabled() {
        return true; // 账户是否启用（默认返回 true）
    }
}