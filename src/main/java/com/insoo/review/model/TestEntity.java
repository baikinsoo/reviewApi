package com.insoo.review.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "test")
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    public TestEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // JPA는 기본 생성자가 필요
    public TestEntity() {
    }

    public void changeNameAndAge(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
