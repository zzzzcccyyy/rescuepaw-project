package com.rescuepaw.controller;

import com.rescuepaw.entity.Animal;
import com.rescuepaw.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private AnimalService animalService;

    // 根据条件搜索动物
    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> searchAnimals(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String gender) {

        Integer page = 0;
        Integer size = 10;
        String sortField = null;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Animal> animalPage = animalService.searchAnimals(type, minAge, maxAge, gender, page, size, sortField, direction);
        List<Animal> animals = animalPage.getContent();
        return ResponseEntity.ok(animals);
    }
}