package com.example.demo.session;

public enum SessionStatus {
    OPEN,        // 모집중(최소인원 미달/달성 여부는 current>=min으로 판단)
    CONFIRMED,   // 최소인원 달성으로 '확정'(확정 문자 발송 트리거)
    FULL,        // 최대인원 도달(모집 마감)
    CANCELED,    // 세션 취소(환불/취소 문자)
    FINISHED     // 세션 종료(정산 대상)
}
