package com.rescuepaw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdoptionFormDto {

    @NotNull(message = "用户ID不能为空")
    private Long userId; // 申请人ID

    @NotNull(message = "动物ID不能为空")
    private Long animalId; // 申请领养的动物ID

    @NotBlank(message = "申请理由不能为空")
    private String applyReason; // 申请理由

    // Getter 和 Setter 方法
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }
}