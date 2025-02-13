package com.rescuepaw.service;

import com.rescuepaw.entity.VolunteerActivity;
import com.rescuepaw.repository.VolunteerActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

// 自定义志愿者活动未找到异常类
class VolunteerActivityNotFoundException extends RuntimeException {
    public VolunteerActivityNotFoundException(String message) {
        super(message);
    }
}

@Service
public class VolunteerService {

    private static final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    @Autowired
    private VolunteerActivityRepository volunteerActivityRepository;

    /**
     * 获取所有志愿者活动
     * @return 志愿者活动列表
     */
    @Transactional(readOnly = true)
    public List<VolunteerActivity> getAllActivities() {
        logger.info("Fetching all volunteer activities");
        return volunteerActivityRepository.findAll();
    }

    /**
     * 添加新的志愿者活动
     * @param activity 要添加的志愿者活动对象
     * @return 保存后的志愿者活动对象
     */
    @Transactional
    public VolunteerActivity addActivity(VolunteerActivity activity) {
        if (activity == null) {
            logger.error("Attempted to add a null volunteer activity");
            throw new IllegalArgumentException("Volunteer activity cannot be null");
        }
        logger.info("Adding a new volunteer activity: {}", activity.getTitle());
        return volunteerActivityRepository.save(activity);
    }

    /**
     * 根据ID获取志愿者活动
     * @param id 志愿者活动的ID
     * @return 对应的志愿者活动对象
     * @throws VolunteerActivityNotFoundException 如果未找到对应的活动
     */
    @Transactional(readOnly = true)
    public VolunteerActivity getActivityById(Long id) {
        if (id == null) {
            logger.error("Attempted to get a volunteer activity with null ID");
            throw new IllegalArgumentException("Activity ID cannot be null");
        }
        logger.info("Fetching volunteer activity with ID: {}", id);
        return volunteerActivityRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Volunteer activity with ID {} not found", id);
                    return new VolunteerActivityNotFoundException("活动未找到: " + id);
                });
    }

    /**
     * 根据标题搜索志愿者活动
     * @param title 搜索的标题关键字
     * @return 符合条件的志愿者活动列表
     */
    @Transactional(readOnly = true)
    public List<VolunteerActivity> searchActivitiesByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            logger.error("Attempted to search activities with null or empty title");
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        logger.info("Searching volunteer activities by title: {}", title);
        return volunteerActivityRepository.findByTitleContaining(title);
    }

    /**
     * 根据地点搜索志愿者活动
     * @param location 搜索的地点关键字
     * @return 符合条件的志愿者活动列表
     */
    @Transactional(readOnly = true)
    public List<VolunteerActivity> searchActivitiesByLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            logger.error("Attempted to search activities with null or empty location");
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        logger.info("Searching volunteer activities by location: {}", location);
        return volunteerActivityRepository.findByLocation(location);
    }

    /**
     * 根据时间范围搜索志愿者活动
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 符合条件的志愿者活动列表
     */
    @Transactional(readOnly = true)
    public List<VolunteerActivity> searchActivitiesByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            logger.error("Attempted to search activities with null start or end time");
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }
        if (startTime.isAfter(endTime)) {
            logger.error("Start time is after end time for activity search");
            throw new IllegalArgumentException("Start time cannot be after end time");
        }
        logger.info("Searching volunteer activities between {} and {}", startTime, endTime);
        return volunteerActivityRepository.findByStartTimeBetween(startTime, endTime);
    }

    /**
     * 删除志愿者活动
     * @param id 要删除的志愿者活动的ID
     */
    @Transactional
    public void deleteActivity(Long id) {
        if (id == null) {
            logger.error("Attempted to delete a volunteer activity with null ID");
            throw new IllegalArgumentException("Activity ID cannot be null");
        }
        logger.info("Deleting volunteer activity with ID: {}", id);
        volunteerActivityRepository.deleteById(id);
    }
    /**
     * 获取志愿者活动的数量
     * @return 志愿者活动的数量
     */
    public long getActivityCount() {
        // 假设这里通过查询数据库获取活动数量
        return volunteerActivityRepository.count();
    }
}