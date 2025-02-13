package com.rescuepaw.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity_registration")
public class ActivityRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 报名用户

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private VolunteerActivity activity; // 报名的活动

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // 报名时间

    // 构造函数
    public ActivityRegistration() {}

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

    public VolunteerActivity getActivity() {
        return activity;
    }

    public void setActivity(VolunteerActivity activity) {
        this.activity = activity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // 在保存前自动设置创建时间
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}