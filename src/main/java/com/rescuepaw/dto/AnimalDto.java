package com.rescuepaw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class AnimalDto {

    @NotNull(message = "更新时动物 ID 不能为空")
    private Long id;

    @NotBlank(message = "动物名称不能为空")
    private String name; // 动物名字

    @NotBlank(message = "动物种类不能为空")
    private String type; // 种类（猫、狗等）

    @NotNull(message = "动物年龄不能为空")
    private Integer age; // 年龄

    private Gender gender; // 性别

    private String healthStatus; // 健康状况

    private String description; // 描述

    private String imageUrl; // 图片链接

    public enum Gender {
        MALE, FEMALE
    }

    public AnimalDto() {
    }

    public AnimalDto(Long id, String name, String type, Integer age, Gender gender, String healthStatus, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.healthStatus = healthStatus;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "AnimalDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", healthStatus='" + healthStatus + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDto animalDto = (AnimalDto) o;
        return Objects.equals(id, animalDto.id) &&
                Objects.equals(name, animalDto.name) &&
                Objects.equals(type, animalDto.type) &&
                Objects.equals(age, animalDto.age) &&
                gender == animalDto.gender &&
                Objects.equals(healthStatus, animalDto.healthStatus) &&
                Objects.equals(description, animalDto.description) &&
                Objects.equals(imageUrl, animalDto.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, age, gender, healthStatus, description, imageUrl);
    }
}