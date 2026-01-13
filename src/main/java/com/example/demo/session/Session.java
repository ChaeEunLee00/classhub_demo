package com.example.demo.session;

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

    private Long onedayClassId; // OnedayClass Aggregate 참조

    private LocalDate date; // 날짜

    private LocalTime startTime; // 시작시간

    private LocalTime endTime; // 종료시간

    @Embedded
    private Capacity capacity;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @Builder
    public Session(Long onedayClassId, LocalDate date, LocalTime startTime, LocalTime endTime,
                   Integer minCapacity, Integer maxCapacity) {
        this.onedayClassId = onedayClassId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = Capacity.of(minCapacity, maxCapacity);
        this.status = SessionStatus.OPEN;
        refreshStatus();
    }

    // 최소수용인원 변경
    public void updateMinCapacity(Integer minCapacity) {
        this.capacity = this.capacity.changeMin(minCapacity);
        refreshStatus();
    }

    // 최대수용인원 변경
    public void updateMaxCapacity(Integer maxCapacity) {
        this.capacity = this.capacity.changeMax(maxCapacity);
        refreshStatus();
    }

    // 예약 시 인원 증가
    public void increaseCapacity(int count) {
        if (this.status == SessionStatus.CANCELED || this.status == SessionStatus.FINISHED) {
            throw new IllegalStateException("취소/종료된 세션은 예약할 수 없습니다.");
        }
        if (this.status == SessionStatus.FULL) {
            throw new IllegalStateException("정원이 가득 찬 세션입니다.");
        }

        this.capacity = this.capacity.increase(count);
        refreshStatus();
    }

    // 예약 취소 시 인원 감소
    public void decreaseCapacity(int count) {
        if (this.status == SessionStatus.CANCELED || this.status == SessionStatus.FINISHED) {
            // 정책적으로 취소/종료된 세션은 인원 변경을 막는 편이 안전
            throw new IllegalStateException("취소/종료된 세션의 인원은 변경할 수 없습니다.");
        }

        this.capacity = this.capacity.decrease(count);
        refreshStatus();
    }

    public boolean isReservable() {
        return this.status == SessionStatus.OPEN || this.status == SessionStatus.CONFIRMED;
    }

    private void refreshStatus() {
        // 취소/종료는 인원 변화로 되돌리지 않음
        if (this.status == SessionStatus.CANCELED || this.status == SessionStatus.FINISHED) {
            return;
        }

        if (this.capacity.isMaxReached()) {
            this.status = SessionStatus.FULL;
            return;
        }

        if (this.capacity.isMinReached()) {
            this.status = SessionStatus.CONFIRMED;
        } else {
            this.status = SessionStatus.OPEN;
        }
    }
}
