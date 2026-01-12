package com.example.demo.instructor;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String zipCode;

    private String address;

    private String addressDetail;

    public Address(String zipCode, String address, String addressDetail) {
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}