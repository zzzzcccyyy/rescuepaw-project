package com.rescuepaw.service;

import com.rescuepaw.entity.AdoptionForm;
import com.rescuepaw.entity.AdoptionForm.AdoptionStatus;
import com.rescuepaw.repository.AdoptionFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {

    @Autowired
    private AdoptionFormRepository adoptionFormRepository;

    // 获取所有领养申请
    public List<AdoptionForm> getAllAdoptionForms() {
        return adoptionFormRepository.findAll();
    }

    // 提交领养申请
    public AdoptionForm submitAdoptionForm(AdoptionForm adoptionForm) {
        return adoptionFormRepository.save(adoptionForm);
    }

    // 更新领养申请状态
    public AdoptionForm updateAdoptionStatus(Long id, AdoptionStatus status) {
        AdoptionForm form = adoptionFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("申请未找到: " + id));
        form.setStatus(status);
        return adoptionFormRepository.save(form);
    }

    // 计算领养成功率
// 领养成功率 = 已批准的领养申请数量 / 总领养申请数量 * 100
// 如果总领养申请数量为 0，则返回 0
    public double calculateSuccessRate() {
        long totalAdoptions = adoptionFormRepository.count();
        if (totalAdoptions == 0) {
            return 0;
        }
        long successfulAdoptions = adoptionFormRepository.countByStatus(AdoptionForm.AdoptionStatus.APPROVED);
        return (double) successfulAdoptions / totalAdoptions * 100;
    }
}