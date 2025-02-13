package com.rescuepaw.repository;

import com.rescuepaw.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

    // 根据种类和性别查询
    List<Animal> findByTypeAndGender(String type, String gender);

    // 根据种类查询
    List<Animal> findByType(String type);

    // 根据性别查询
    List<Animal> findByGender(String gender);

    // 根据种类、年龄范围和性别查询
    @Query("SELECT a FROM Animal a WHERE a.type = :type AND a.age BETWEEN :minAge AND :maxAge AND a.gender = :gender")
    List<Animal> findByTypeAndAgeBetweenAndGender(
            @Param("type") String type,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("gender") String gender);
}