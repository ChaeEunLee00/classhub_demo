package com.example.demo.OnedayClass;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sessions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // 날짜

    private LocalTime startTime; // 시작시간

    private LocalTime endTime; // 종료시간

    private Integer minCapacity; // 최소인원

    private Integer maxCapacity; // 최대인원

    private Integer currentCapacity; // 현재인원(예약/신청된 인원)

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @Builder
    public Session(LocalDate date, LocalTime startTime, LocalTime endTime,
                   Integer minCapacity, Integer maxCapacity) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.status = SessionStatus.OPEN;
    }

    // 최소수용인원 변경 메서드
    public void updateMinCapacity(Integer minCapacity) {
        this.minCapacity = minCapacity;
    }

    // 최대수용인원 변경 메서드
    public void updateMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // 예약시 인원 증가 메서드
    public void increaseCapacity(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count는 1 이상이어야 합니다.");
        }
        if (this.status == SessionStatus.CANCELED || this.status == SessionStatus.FINISHED) {
            throw new IllegalStateException("취소/종료된 세션은 예약할 수 없습니다.");
        }
        if (this.status == SessionStatus.FULL) {
            throw new IllegalStateException("정원이 가득 찬 세션입니다.");
        }

            int next = this.currentCapacity + count;
            if (next > this.maxCapacity) {
                throw new IllegalStateException("최대 수용인원을 초과할 수 없습니다.");
            }

            this.currentCapacity = next;

            // 상태 전이
            if (this.currentCapacity >= this.maxCapacity) {
                this.status = SessionStatus.FULL;
                return;
            }

            // 최소수용인원 달성하면 CONFIRMED
            if (this.currentCapacity >= this.minCapacity) {
                this.status = SessionStatus.CONFIRMED;
            }
    }

    // 예약 취소 시 인원 감소 메서드
    public void decreaseCapacity(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count는 1 이상이어야 합니다.");
        }

        int next = this.currentCapacity - count;
        if (next < 0) {
            throw new IllegalStateException("현재 인원은 0 미만이 될 수 없습니다.");
        }

        this.currentCapacity = next;

        // 상태 전이
        // FULL이었다가 인원이 줄면 -> min 기준으로 CONFIRMED/OPEN
        if (this.currentCapacity >= this.minCapacity) {
            // 자리 남으면 FULL 유지 X
            this.status = SessionStatus.CONFIRMED;
        } else {
            this.status = SessionStatus.OPEN;
        }
    }
}
