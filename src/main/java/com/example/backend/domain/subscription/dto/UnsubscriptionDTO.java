package com.example.backend.domain.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnsubscriptionDTO {
    private Long id;  // subscription.id
    private String name;  // subscription.name
    private String subImgUrl; // subscription_img.subscription_img_url
}
