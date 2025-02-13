package com.rescuepaw.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_form")
public class AdoptionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 申请人

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal; // 申请领养的动物

    @Column(name = "apply_reason", nullable = false)
    private String applyReason; // 申请理由

    @Column(name = "status", nullable = false)
    private AdoptionStatus status = AdoptionStatus.PENDING; // 申请状态（默认 PENDING）

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 申请时间

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 更新时间

    // 构造函数
    public AdoptionForm() {
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // 保存时设置创建时间和更新时间
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 更新时设置更新时间
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum AdoptionStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}