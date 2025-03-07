package com.example.backend.domain.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendDTO {
    private int subscription_id;         // `sub.id`
    private Long price;                  // `sub.price`
    private int category_id;              // `sub.category_id`
    private LocalDateTime created_at;     // `sub.created_at`
    private LocalDateTime deleted_at;     // `sub.deleted_at`
    private LocalDateTime modified_at;    // `sub.modified_at`
    private String content;               // `sub.content`
    private String homepage;              // `sub.homepage`
    private String name;                  // `sub.name`
    private int subscription_img_id;      // `sub_img.id`
    private String subscription_img_url;  // `sub_img.subscription_img_url`
}
