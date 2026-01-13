package com.example.demo.OnedayClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Material {

    private String content; // 준비물/재료 내용

    public Material(String content) {
        this.content = content;
    }
}
