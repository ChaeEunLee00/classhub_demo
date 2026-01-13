package com.example.demo.OnedayClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParkingGuide {

    private boolean parkingAvailable; // 주차 가능 여부

    private String parkingInfo; // 주차 안내 정보

    public ParkingGuide(boolean parkingAvailable, String parkingInfo) {
        this.parkingAvailable = parkingAvailable;
        this.parkingInfo = parkingInfo;
    }
}
