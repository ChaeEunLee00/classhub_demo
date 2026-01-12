package com.example.demo.member;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationSetting {

    private boolean smsEnabled;   // 문자 알림 (기본)
    private boolean emailEnabled; // 이메일 알림 (선택)

    public NotificationSetting(boolean emailEnabled) {
        this.smsEnabled = true;
        this.emailEnabled = emailEnabled;
    }
}