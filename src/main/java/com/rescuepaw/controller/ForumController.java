package com.rescuepaw.controller;

import com.rescuepaw.entity.ForumPost;
import com.rescuepaw.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    // 获取所有帖子
    @GetMapping("/posts")
    public ResponseEntity<List<ForumPost>> getAllPosts() {
        List<ForumPost> posts = forumService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 发布新帖子
    @PostMapping("/posts")
    public ResponseEntity<ForumPost> addPost(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String content) {
        ForumPost post = forumService.addPost(userId, title, content);
        return ResponseEntity.ok(post);
    }

    // 根据ID获取帖子
    @GetMapping("/posts/{id}")
    public ResponseEntity<ForumPost> getPostById(@PathVariable Long id) {
        ForumPost post = forumService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // 搜索帖子
    @GetMapping("/posts/search")
    public ResponseEntity<List<ForumPost>> searchPosts(@RequestParam String keyword) {
        List<ForumPost> posts = forumService.searchPosts(keyword);
        return ResponseEntity.ok(posts);
    }
}