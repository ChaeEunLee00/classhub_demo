package com.example.demo.instructor;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "instructors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String phone;

    @Embedded
    private Address address; // 주소

    @Embedded
    private BusinessInfo businessInfo; // 사업자 관련

    @Embedded
    private SettlementAccount settlementAccount; // 정산 계좌

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Instructor(String loginId, String password, String name, String phone,
                      Address address, BusinessInfo businessInfo,
                      SettlementAccount settlementAccount) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.businessInfo = (businessInfo != null ? businessInfo : BusinessInfo.personal());
        this.settlementAccount = settlementAccount;
        this.createdAt = LocalDateTime.now();
    }
}
