package com.example.demo.session;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Capacity {

    private Integer minCapacity;

    private Integer maxCapacity;

    private Integer currentCapacitiy;

    private Capacity(Integer minCapacity, Integer maxCapacity, Integer current) {
        validate(minCapacity, maxCapacity, current);
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.currentCapacitiy = current;
    }

    public static Capacity of(Integer min, Integer max) {
        return new Capacity(min, max, 0);
    }

    public Capacity changeMin(Integer newMin) {
        return new Capacity(newMin, this.maxCapacity, this.currentCapacitiy);
    }

    public Capacity changeMax(Integer newMax) {
        return new Capacity(this.minCapacity, newMax, this.currentCapacitiy);
    }

    public Capacity increase(int count) {
        if (count <= 0) throw new IllegalArgumentException("count는 1 이상이어야 합니다.");
        return new Capacity(this.minCapacity, this.maxCapacity, this.currentCapacitiy + count);
    }

    public Capacity decrease(int count) {
        if (count <= 0) throw new IllegalArgumentException("count는 1 이상이어야 합니다.");
        return new Capacity(this.minCapacity, this.maxCapacity, this.currentCapacitiy - count);
    }

    public boolean isMinReached() {
        return this.currentCapacitiy >= this.maxCapacity;
    }

    public boolean isMaxReached() {
        return this.currentCapacitiy >= this.maxCapacity;
    }

    private static void validate(Integer min, Integer max, Integer current) {
        if (min == null || max == null || current == null) {
            throw new IllegalArgumentException("min/max/current는 null일 수 없습니다.");
        }
        if (min < 0 || max <= 0) {
            throw new IllegalArgumentException("min은 0 이상, max는 1 이상이어야 합니다.");
        }
        if (min > max) {
            throw new IllegalArgumentException("min은 max보다 클 수 없습니다.");
        }
        if (current < 0) {
            throw new IllegalArgumentException("current는 0 미만이 될 수 없습니다.");
        }
        if (current > max) {
            throw new IllegalArgumentException("current는 max를 초과할 수 없습니다.");
        }
    }
}
