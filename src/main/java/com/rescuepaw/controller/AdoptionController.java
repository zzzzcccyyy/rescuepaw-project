package com.rescuepaw.controller;

import com.rescuepaw.entity.AdoptionForm;
import com.rescuepaw.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    // 获取所有领养申请
    @GetMapping
    public ResponseEntity<List<AdoptionForm>> getAllAdoptionForms() {
        List<AdoptionForm> forms = adoptionService.getAllAdoptionForms();
        return ResponseEntity.ok(forms);
    }

    // 提交领养申请
    @PostMapping
    public ResponseEntity<AdoptionForm> submitAdoptionForm(@RequestBody AdoptionForm adoptionForm) {
        AdoptionForm savedForm = adoptionService.submitAdoptionForm(adoptionForm);
        return ResponseEntity.ok(savedForm);
    }

    // 更新领养申请状态（需管理员权限）
    @PutMapping("/{id}/status")
    public ResponseEntity<AdoptionForm> updateAdoptionStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            // 将 String 类型的 status 转换为 AdoptionForm.AdoptionStatus 枚举类型
            AdoptionForm.AdoptionStatus adoptionStatus = AdoptionForm.AdoptionStatus.valueOf(status);
            AdoptionForm updatedForm = adoptionService.updateAdoptionStatus(id, adoptionStatus);
            return ResponseEntity.ok(updatedForm);
        } catch (IllegalArgumentException e) {
            // 处理传入的状态字符串不是合法枚举值的情况
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}