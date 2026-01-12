package com.example.demo.OnedayClass;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "oneday_class_id")
    private List<Session> sessions = new ArrayList<>();

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

    // 클래스 세션 추가
    public void addSession(Session session) {
        // 날짜 시간 중복 확인
        boolean isDuplicate = this.sessions.stream()
                .anyMatch(s -> s.getDate().equals(session.getDate())
                        && s.getStartTime().equals(session.getStartTime()));

        if (isDuplicate) {
            throw new IllegalStateException("동일한 날짜와 시간에 이미 세션이 존재합니다.");
        }

        this.sessions.add(session);
    }

    public void removeSession(Session session) {
        this.sessions.remove(session);
    }
}
