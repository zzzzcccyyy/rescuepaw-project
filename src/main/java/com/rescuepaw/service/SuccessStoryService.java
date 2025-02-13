package com.rescuepaw.service;

import com.rescuepaw.entity.SuccessStory;
import com.rescuepaw.repository.SuccessStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuccessStoryService {

    @Autowired
    private SuccessStoryRepository successStoryRepository;

    // 获取所有领养成功故事
    public List<SuccessStory> getAllSuccessStories() {
        return successStoryRepository.findAll();
    }

    // 添加新的领养成功故事
    public SuccessStory addSuccessStory(SuccessStory successStory) {
        return successStoryRepository.save(successStory);
    }

    // 更新领养成功故事
    public SuccessStory updateSuccessStory(Long id, SuccessStory storyDetails) {
        SuccessStory story = successStoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("故事未找到: " + id));
        story.setTitle(storyDetails.getTitle());
        story.setContent(storyDetails.getContent());
        return successStoryRepository.save(story);
    }

    // 删除领养成功故事
    public void deleteSuccessStory(Long id) {
        successStoryRepository.deleteById(id);
    }
}