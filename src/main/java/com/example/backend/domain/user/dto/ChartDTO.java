package com.example.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartDTO {
    // Chart1
    private String expenditureName;
    private int expenditureAmount;

    // Chart2
    private String subName;
    private int subAmount;
}
