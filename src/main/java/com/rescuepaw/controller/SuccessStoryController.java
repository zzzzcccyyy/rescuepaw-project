package com.rescuepaw.controller;

import com.rescuepaw.entity.SuccessStory;
import com.rescuepaw.service.SuccessStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/success-stories")
public class SuccessStoryController {

    @Autowired
    private SuccessStoryService successStoryService;

    // 获取所有领养成功故事
    @GetMapping
    public ResponseEntity<List<SuccessStory>> getAllSuccessStories() {
        List<SuccessStory> stories = successStoryService.getAllSuccessStories();
        return ResponseEntity.ok(stories);
    }

    // 添加新的领养成功故事（管理员权限）
    @PostMapping
    public ResponseEntity<SuccessStory> addSuccessStory(@RequestBody SuccessStory successStory) {
        SuccessStory savedStory = successStoryService.addSuccessStory(successStory);
        return ResponseEntity.ok(savedStory);
    }

    // 更新领养成功故事（管理员权限）
    @PutMapping("/{id}")
    public ResponseEntity<SuccessStory> updateSuccessStory(@PathVariable Long id, @RequestBody SuccessStory storyDetails) {
        SuccessStory updatedStory = successStoryService.updateSuccessStory(id, storyDetails);
        return ResponseEntity.ok(updatedStory);
    }

    // 删除领养成功故事（管理员权限）
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuccessStory(@PathVariable Long id) {
        successStoryService.deleteSuccessStory(id);
        return ResponseEntity.ok("领养成功故事删除成功");
    }
}