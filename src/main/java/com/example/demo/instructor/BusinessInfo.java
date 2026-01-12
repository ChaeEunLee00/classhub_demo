package com.example.demo.instructor;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfo {

    private boolean isBusiness; // 개인/사업자 구분

    private String registrationNo; // 사업자등록번호

    private String businessName; // 상호(법인명)

    private String representativeName; // 대표자명

    // 관련 내용 필요 시 추가

    private BusinessInfo(
            boolean isBusiness,
            String registrationNo,
            String businessName,
            String representativeName
    ) {
        this.isBusiness = isBusiness;
        this.registrationNo = registrationNo;
        this.businessName = businessName;
        this.representativeName = representativeName;
    }

    public static BusinessInfo personal() {
        return new BusinessInfo(false, null, null, null);
    }

    public static BusinessInfo business(
            String registrationNo,
            String businessName,
            String representativeName
    ) {
        return new BusinessInfo(true, registrationNo, businessName, representativeName);
    }
}
