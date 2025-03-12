package com.example.backend.domain.subscription.dto;

import com.example.backend.domain.subscription.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;

    // Category 엔티티를 받아서 DTO로 변환하는 생성자
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 엔티티 -> DTO 변환 생성자
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
