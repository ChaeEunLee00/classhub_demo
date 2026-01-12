package com.example.demo.reservation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSession {

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(nullable = false)
    private Long capacity;

    @Builder
    public ReservationSession(Long sessionId, Long capacity) {
        this.sessionId = sessionId;
        this.capacity = capacity;
    }
}
