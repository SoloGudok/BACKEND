package com.example.backend.domain.user.service;

import com.example.backend.domain.user.dto.RecommendDTO;
import com.example.backend.domain.user.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {
    private final RecommendRepository recommendRepository;


    @Override
    public List<RecommendDTO> getRecommendations() {
        List<Object[]> results = recommendRepository.getSubscriptionRecommendations();
        return results.stream()
                .map(this::convertToDTO) // Object[] → RecommendDTO 변환
                .collect(Collectors.toList());
    }

    @Override
    public RecommendDTO convertToDTO(Object[] row) {
        return RecommendDTO.builder()
                .price(((Number) row[0]).longValue())          // sub.price
                .category_id(((Number) row[1]).intValue())     // sub.category_id
                .created_at(convertToLocalDateTime(row[2]))           // sub.created_at
                .deleted_at(convertToLocalDateTime(row[3]))           // sub.deleted_at
                .subscription_id(((Number) row[4]).intValue())  // sub.id
                .modified_at(convertToLocalDateTime(row[5]))          // sub.modified_at
                .content((String) row[6])                     // sub.content
                .homepage((String) row[7])                    // sub.homepage
                .name((String) row[8])                        // sub.name
                .subscription_img_id(((Number) row[9]).intValue()) // sub_img.id
                .subscription_img_url((String) row[10])       // sub_img.subscription_img_url
                .build();
    }

    @Override
    public List<RecommendDTO> getCardRecommendations() {
        List<Object[]> results = recommendRepository.getCardRecommendations();
        return results.stream()
                .map(this::convertCardToDTO) // Object[] → RecommendDTO 변환
                .collect(Collectors.toList());
    }

    @Override
    public RecommendDTO convertCardToDTO(Object[] row) {
        return RecommendDTO.builder()
                .created_at(convertToLocalDateTime(row[0]))
                .deleted_at(convertToLocalDateTime(row[1]))
                .card_id(((Number) row[2]).intValue())
                .modified_at(convertToLocalDateTime(row[3]))
                .card_name((String) row[4])
                .description((String) row[5])
                .short_description((String) row[6])
                .category_id(((Number) row[7]).intValue())
                .card_img_id(((Number) row[8]).intValue())
                .card_img_url((String) row[9])
                .build();
    }

    /*
    timestamp를 LocalDateTime으로 바꾸는 작업 - 완성
    (made by 민규)
    */
    private LocalDateTime convertToLocalDateTime(Object timestamp) {
        if (timestamp instanceof Timestamp) {
            return ((Timestamp) timestamp).toLocalDateTime();
        }
        return null;
    }

}
