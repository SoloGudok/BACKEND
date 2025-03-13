package com.example.backend.domain.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniUnsubscriptionDTO {
    private String email;
    private String password;
    private Long serviceId; // Long subscriptionId
}
