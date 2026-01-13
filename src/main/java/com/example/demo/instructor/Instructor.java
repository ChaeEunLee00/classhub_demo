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
    private BusinessInfo businessInfo; // 사업자 관련 (주소 포함)

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "settlement_account_id")
    private SettlementAccount settlementAccount; // 정산 계좌

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Instructor(String loginId, String password, String name, String phone,
                      BusinessInfo businessInfo, SettlementAccount settlementAccount) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.businessInfo = businessInfo;
        this.settlementAccount = settlementAccount;
        this.createdAt = LocalDateTime.now();
    }
}
