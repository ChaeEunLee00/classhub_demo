package com.example.demo.OnedayClass;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OnedayClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long instructorId;

    private String title;

    private String description;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private OnedayClassStatus status; // 오픈, 폐강

    private LocalDateTime createdAt;

    @Builder
    public OnedayClass(Long instructorId, String title, String description,
                       Category category, Integer price) {
        this.instructorId = instructorId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.status = OnedayClassStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    // 클래스 정보 변경
    public void updateClassInfo(String title, String description, Category category,
                                Integer price) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }
}
