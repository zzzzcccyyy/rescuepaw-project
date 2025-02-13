package com.rescuepaw.controller;

import com.rescuepaw.entity.Animal;
import com.rescuepaw.service.AnimalService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080") // 允许来自 http://localhost:8081 的跨域请求
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // 获取所有动物信息
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        log.info("Getting all animals");
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    // 根据ID获取动物信息
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        log.info("Getting animal by ID: {}", id);
        Optional<Animal> animal = animalService.getAnimalById(id);
        return animal.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.info("Animal with ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // 添加新动物（需管理员权限）
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Animal> addAnimal(@Valid @RequestBody Animal animal) {
        log.info("Adding a new animal");
        Animal savedAnimal = animalService.addAnimal(animal);
        return ResponseEntity.ok(savedAnimal);
    }

    // 更新动物信息（需管理员权限）
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal animalDetails) {
        log.info("Updating animal with ID: {}", id);
        Animal updatedAnimal = animalService.updateAnimal(id, animalDetails);
        return ResponseEntity.ok(updatedAnimal);
    }

    // 删除动物信息（需管理员权限）
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        log.info("Deleting animal with ID: {}", id);
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}