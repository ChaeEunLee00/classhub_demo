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

    // 주소 정보
    private String zipCode;

    private String address;

    private String addressDetail;

    private BusinessInfo(
            boolean isBusiness,
            String registrationNo,
            String businessName,
            String representativeName,
            String zipCode,
            String address,
            String addressDetail
    ) {
        this.isBusiness = isBusiness;
        this.registrationNo = registrationNo;
        this.businessName = businessName;
        this.representativeName = representativeName;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
    }

    public static BusinessInfo personal(String zipCode, String address, String addressDetail) {
        return new BusinessInfo(false, null, null, null, zipCode, address, addressDetail);
    }

    public static BusinessInfo business(
            String registrationNo,
            String businessName,
            String representativeName,
            String zipCode,
            String address,
            String addressDetail
    ) {
        return new BusinessInfo(true, registrationNo, businessName, representativeName,
                zipCode, address, addressDetail);
    }
}
