package com.rescuepaw.service;

import com.rescuepaw.entity.Animal;
import com.rescuepaw.exception.InvalidParameterException;
import com.rescuepaw.repository.AnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);
    private static final List<String> VALID_GENDERS = List.of("MALE", "FEMALE");
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private AnimalRepository animalRepository;

    // 获取所有动物信息
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // 根据ID获取动物信息
    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    // 添加新动物
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // 更新动物信息
    public Animal updateAnimal(Long id, Animal animalDetails) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id: " + id));
        animal.setName(animalDetails.getName());
        animal.setType(animalDetails.getType());
        animal.setAge(animalDetails.getAge());
        animal.setGender(animalDetails.getGender());
        animal.setHealthStatus(animalDetails.getHealthStatus());
        animal.setDescription(animalDetails.getDescription());
        animal.setImageUrl(animalDetails.getImageUrl());
        return animalRepository.save(animal);
    }

    // 删除动物信息
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    /**
     * 根据条件搜索动物（带分页）
     * @param type      动物类型（可选）
     * @param minAge    最小年龄（可选）
     * @param maxAge    最大年龄（可选）
     * @param gender    性别（可选，MALE/FEMALE）
     * @param page      页码（从0开始，默认0）
     * @param size      每页数量（默认10）
     * @param sortField 排序字段（可选）
     * @param direction 排序方向（ASC/DESC，默认ASC）
     * @return 分页的动物数据
     */
    public Page<Animal> searchAnimals(String type,
                                      Integer minAge,
                                      Integer maxAge,
                                      String gender,
                                      Integer page,
                                      Integer size,
                                      String sortField,
                                      Sort.Direction direction) {
        // 参数校验
        validateAgeRange(minAge, maxAge);
        validateGender(gender);

        // 构建分页和排序
        Pageable pageable = buildPageable(page, size, sortField, direction);

        // 构建查询条件
        Specification<Animal> spec = buildSearchSpecification(type, minAge, maxAge, gender);

        try {
            return animalRepository.findAll(spec, pageable);
        } catch (Exception e) {
            logger.error("动物搜索失败 | 参数: type={}, minAge={}, maxAge={}, gender={} | 错误: {}",
                    type, minAge, maxAge, gender, e.getMessage());
            throw new RuntimeException("查询动物信息时发生服务器错误", e);
        }
    }

    /**
     * 构建动态查询条件
     */
    private Specification<Animal> buildSearchSpecification(String type,
                                                           Integer minAge,
                                                           Integer maxAge,
                                                           String gender) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (type != null && !type.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("type")), type.toLowerCase()));
            }
            if (minAge != null) {
                predicates.add(cb.ge(root.get("age"), minAge));
            }
            if (maxAge != null) {
                predicates.add(cb.le(root.get("age"), maxAge));
            }
            if (gender != null && !gender.isBlank()) {
                predicates.add(cb.equal(cb.upper(root.get("gender")), gender.toUpperCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * 参数校验方法
     */
    private void validateAgeRange(Integer minAge, Integer maxAge) {
        if (minAge != null && minAge < 0) {
            throw new InvalidParameterException("最小年龄不能为负数");
        }
        if (maxAge != null && maxAge < 0) {
            throw new InvalidParameterException("最大年龄不能为负数");
        }
        if (minAge != null && maxAge != null && minAge > maxAge) {
            throw new InvalidParameterException("最小年龄不能大于最大年龄");
        }
    }

    private void validateGender(String gender) {
        if (gender != null && !VALID_GENDERS.contains(gender.toUpperCase())) {
            throw new InvalidParameterException("非法性别参数，允许值: " + VALID_GENDERS);
        }
    }

    /**
     * 构建分页请求
     */
    private Pageable buildPageable(Integer page, Integer size, String sortField, Sort.Direction direction) {
        int pageNumber = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? size : DEFAULT_PAGE_SIZE;

        Sort sort = Sort.unsorted();
        if (sortField != null && !sortField.isBlank()) {
            direction = (direction != null) ? direction : Sort.Direction.ASC;
            sort = Sort.by(direction, sortField);
        }

        return PageRequest.of(pageNumber, pageSize, sort);
    }
}