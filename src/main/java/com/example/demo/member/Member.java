package com.example.demo.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String email;

    private String phone;

    private LocalDateTime createdAt;

    @Embedded
    private NotificationSetting notificationSetting;

    @Builder
    public Member(String loginId, String password, String name, String email, String phone,
                  NotificationSetting notificationSetting) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.notificationSetting = notificationSetting;
        this.createdAt = LocalDateTime.now();
    }
}
