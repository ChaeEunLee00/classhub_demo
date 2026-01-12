package com.example.demo.payment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private Long reservationId;

    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

    @Enumerated(EnumType.STRING)
    private PaymentProvider provider;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Payment(Integer amount, Long reservationId, PaymentMethodType paymentMethodType, PaymentProvider provider) {
        this.amount = amount;
        this.reservationId = reservationId;
        this.paymentMethodType = paymentMethodType;
        this.provider = provider;
        this.status = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void completePayment(String transactionId) {
        this.transactionId = transactionId;
        this.status = PaymentStatus.COMPLETED;
        this.paidAt = LocalDateTime.now();
    }

    public void failPayment() {
        this.status = PaymentStatus.FAILED;
    }

    public void refund() {
        if (this.status != PaymentStatus.COMPLETED) {
            throw new IllegalStateException("완료된 결제만 환불할 수 있습니다.");
        }
        this.status = PaymentStatus.REFUNDED;
    }

    public boolean isCompleted() {
        return this.status == PaymentStatus.COMPLETED;
    }

    public boolean isPending() {
        return this.status == PaymentStatus.PENDING;
    }
}
