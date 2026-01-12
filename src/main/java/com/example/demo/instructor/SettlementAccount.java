package com.example.demo.instructor;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementAccount {

    private String bankName; // 은행

    private String accountNumber; // 계좌

    private String accountHolder; // 예금주

    public SettlementAccount(String bankName, String accountNumber, String accountHolder) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }
}