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

    @Embedded
    private Curriculum curriculum; // 커리큘럼

    @Embedded
    private Material material; // 준비물/재료

    @Embedded
    private ClassLocation location; // 수업 장소

    @Embedded
    private ParkingGuide parkingGuide; // 주차 안내

    private LocalDateTime createdAt;

    @Builder
    public OnedayClass(Long instructorId, String title, String description,
                       Category category, Integer price,
                       Curriculum curriculum, Material material,
                       ClassLocation location, ParkingGuide parkingGuide) {
        this.instructorId = instructorId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.curriculum = curriculum;
        this.material = material;
        this.location = location;
        this.parkingGuide = parkingGuide;
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
