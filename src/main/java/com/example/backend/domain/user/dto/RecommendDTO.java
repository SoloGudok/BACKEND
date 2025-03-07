package com.example.backend.domain.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendDTO {
    // 구독서비스 추천
    private int subscription_id;         // `sub.id`
    private Long price;                  // `sub.price`
    private int category_id;              // `sub.category_id` `cd.category_id`
    private LocalDateTime created_at;     // `sub.created_at` `cd.created_at`
    private LocalDateTime deleted_at;     // `sub.deleted_at` `cd.deleted_at`
    private LocalDateTime modified_at;    // `sub.modified_at` `cd.modified_at`
    private String content;               // `sub.content`
    private String homepage;              // `sub.homepage`
    private String name;                  // `sub.name`
    private int subscription_img_id;      // `sub_img.id`
    private String subscription_img_url;  // `sub_img.subscription_img_url`

    // 카드 추천
    private int card_id;                  // `cd.id`
    private String card_name;             // `cd.card_name`
    private String description;           // `cd.description`
    private String short_description;     // `cd.short_description`
    private int card_img_id;              // `cd_img.card_img_id`
    private String card_img_url;          // `cd_img.card_img_url`
}
