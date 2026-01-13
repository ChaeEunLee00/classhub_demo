package com.example.demo.OnedayClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Curriculum {

    private String content; // 커리큘럼 내용

    public Curriculum(String content) {
        this.content = content;
    }
}
