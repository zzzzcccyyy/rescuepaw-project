package com.rescuepaw.service;

import com.rescuepaw.entity.ForumPost;
import com.rescuepaw.entity.User;
import com.rescuepaw.repository.ForumPostRepository;
import com.rescuepaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    @Autowired
    private ForumPostRepository forumPostRepository;

    @Autowired
    private UserRepository userRepository;

    // 获取所有帖子
    public List<ForumPost> getAllPosts() {
        return forumPostRepository.findAll();
    }

    // 添加新帖子
    public ForumPost addPost(Long userId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户未找到: " + userId));

        ForumPost post = new ForumPost();
        post.setUser(user);
        post.setTitle(title);
        post.setContent(content);
        return forumPostRepository.save(post);
    }

    // 根据ID获取帖子
    public ForumPost getPostById(Long id) {
        return forumPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("帖子未找到: " + id));
    }

    // 根据关键字搜索帖子
    public List<ForumPost> searchPosts(String keyword) {
        return forumPostRepository.findByTitleContaining(keyword);
    }
}