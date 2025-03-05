package com.example.backend.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenditure")
@Tag(name = "Expenditure", description = "소비내역 관련 api입니다.")
public class ExpenditureController {
}
