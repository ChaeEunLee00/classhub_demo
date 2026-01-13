package com.example.demo.instructor;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "settlement_accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName; // 은행

    private String accountNumber; // 계좌

    private String accountHolder; // 예금주

    @Builder
    public SettlementAccount(String bankName, String accountNumber, String accountHolder) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }
}
