package com.example.demo.OnedayClass;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassLocation {

    private String zipCode; // 우편번호

    private String address; // 주소

    private String addressDetail; // 상세주소

    public ClassLocation(String zipCode, String address, String addressDetail) {
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
