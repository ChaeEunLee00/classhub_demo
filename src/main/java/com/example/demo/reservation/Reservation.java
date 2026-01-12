package com.example.demo.reservation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long classId;

    @Embedded
    private ReservationSession reservationSession;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Reservation(Long userId, Long classId) {
        this.userId = userId;
        this.classId = classId;
        this.status = ReservationStatus.RESERVED;
        this.createdAt = LocalDateTime.now();
    }

    // 예약 상태 confirmed로 변경
    public void confirm() {
        if (this.status == ReservationStatus.CANCELED) {
            throw new IllegalStateException("취소된 예약은 확정할 수 없습니다.");
        }
        this.status = ReservationStatus.CONFIRMED;
        // 안내문자 발송 로직 호출 필요
    }
}
