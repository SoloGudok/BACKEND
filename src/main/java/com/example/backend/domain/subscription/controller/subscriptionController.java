package com.example.backend.domain.subscription.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.domain.subscription.dto.CombinationSubscriptionResponseDto;
import com.example.backend.domain.subscription.dto.SubscriptionDTO;
import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.dto.SubscriptionResponseDto;
import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscription")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Subscription", description = "구독 관련 api입니다.")
@SessionAttributes("selectedSubscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // 카테고리별 구독 리스트 조회
    @GetMapping("/category/{categoryId}")
    public List<SubscriptionRes> getSubscriptionsByCategoryId(@PathVariable Long categoryId) {
        return subscriptionService.getSubscriptionsByCategoryId(categoryId);
    }

    @CrossOrigin(origins = "http://localhost:3000")  // React 앱의 URL로 설정
    @GetMapping("/category/{categoryId}/dto")
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptionsByCategory(@PathVariable Long categoryId) {
        List<SubscriptionDTO> subscriptions = subscriptionService.getSubscriptionsByCategory(categoryId);
        return ResponseEntity.ok(subscriptions);
    }



    @GetMapping("/individual")
    public ResponseEntity<List<SubscriptionResponseDto>> getIndividualSubscriptions(@RequestParam Long userId) {
        return ResponseEntity.ok(subscriptionService.getIndividualSubscriptions(userId));
    }

    @GetMapping("/combination")
    public ResponseEntity<List<CombinationSubscriptionResponseDto>> getCombinationSubscriptions(@RequestParam Long userId) {
        return ResponseEntity.ok(subscriptionService.getCombinationSubscriptions(userId));
    }

    @PostMapping("/select")
    public ResponseEntity<List<SubscriptionDTO>> selectSubscriptions(@RequestBody List<Long> subscriptionIds) {
        // subscriptionIds 리스트에 포함된 각 ID에 대해 구독 서비스 정보를 조회
        List<SubscriptionDTO> subscriptions = subscriptionIds.stream()
                .map(subscriptionService::getSubscriptionById) // ID로 구독 정보 조회
                .collect(Collectors.toList()); // 리스트로 변환

        // 조회된 구독 서비스 리스트를 반환
        return ResponseEntity.ok(subscriptions);
    }


    @PutMapping("/unselect/{id}")
    public ResponseEntity<String> unselectSubscription(@PathVariable Long id) {
        boolean isUnselected = subscriptionService.unselectSubscriptionById(id);
        if (isUnselected) {
            return ResponseEntity.ok("Subscription unselected successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found.");
        }
    }
    /**
     * 모든 구독 서비스 목록을 조회하는 API
     * @return 구독 서비스 리스트
     */
    @GetMapping
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    //Edited by Hyenho
    // 고객 이름
    String _customerName = "예진";

    // 바차트 명칭들 (주요 구독리스트)
    String[] _barChartLabels = {"식품","홈/라이프", "구독", "기타"};
    // 바차트 수치
    float[] _barChart = {10.5f, 8.3f, 6,8f, 4.3f};
    // 도넛 차트 명칭들 (주요 소비 내역)
    String[] _doughnutChartLabels = {"홈/라이프","식품", "구독", "기타"};
    // 도넛 차트 수치
    float[] _doughnutChart = {4.6f, 3.7f, 7.2f, 3.3f };

    // 광고 이미지들
    String[] _advertisement_images ={
            "https://swiperjs.com/demos/images/nature-1.jpg",
            "https://swiperjs.com/demos/images/nature-2.jpg",
            "https://swiperjs.com/demos/images/nature-3.jpg",
            "https://swiperjs.com/demos/images/nature-4.jpg",
            "https://swiperjs.com/demos/images/nature-5.jpg",
            "https://swiperjs.com/demos/images/nature-6.jpg",
            "https://swiperjs.com/demos/images/nature-7.jpg"
    };

    // 고객이 구독중인 서비스들
    String[] _subscriptionService_images = {
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/Healthcare_quat.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/Healthcare_pilatesology.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/homelife_galaxyAIsubscribeClub.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/homelife_salgu.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/homelife_snow.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_xboxgamepass.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_playstationplus.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_eaplay.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_geforcenow.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_ubsoftplus.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_applearcade.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/game_googleplaypass.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/it_adobecreativeclud.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/it_dropbox.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/it_notion.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/it_zoom.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/it_slack.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/it_chatgpt.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_pocketsalad.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_yumsalad.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_haruHankki.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_momplay.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_avecpapa.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_dorikkachim.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_meally.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/food_freshcode.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/selfdevelop_welaaa.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/selfdevelop_publy.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/beauty_amore.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/beauty_basup.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_netflix.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_youtube.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_disney.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_watcha.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_tiving.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_appletv.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/ott_wavve.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/music_spotify.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/music_applemusic.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/music_genie.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/music_melon.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/music_bugs.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/book_millie.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/book_ridiselect.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/book_cremaclub.png",
            "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/book_sam.png"
    };

    String[][] _recommendationSubscription = {
        {"Netflix","https://w7.pngwing.com/pngs/153/31/png-transparent-netflix-macos-bigsur-icon-thumbnail.png"},
        {"wavve","https://w7.pngwing.com/pngs/153/31/png-transparent-netflix-macos-bigsur-icon-thumbnail.png"},
        {"tiving","https://w7.pngwing.com/pngs/153/31/png-transparent-netflix-macos-bigsur-icon-thumbnail.png"}
    };

    // 고객에게 추천하는 카드
    String[][] _recommendationCard = {
            { "신한카드 We Healing","쇼츠디스크립션입니다1","디스크립션입니다1","https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditPOCDMG.gif" },
            { "신한카드 콰트케어","쇼츠디스크립션입니다2","디스크립션입니다2", "https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditBOADNT.gif" },
            { "신한카드 SmartNest","쇼츠디스크립션입니다3","디스크립션입니다3", "https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditBSCDNG.gif" },
            { "신한카드 스노우VIP+","쇼츠디스크립션입니다4","디스크립션입니다4", "https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditPOADHL.gif" }
    };

    @GetMapping("/sendDashboardData")
    public ResponseEntity<Map<String, Object>> getData() {
        Map<String, Object> response = new HashMap<>();
        response.put("advertisementimages", _advertisement_images);
        response.put("customerName", _customerName);
        response.put("barChart", _barChart);
        response.put("barChartLabels", _barChartLabels);
        response.put("doughnutChart", _doughnutChart);
        response.put("doughnutChartLabels", _doughnutChartLabels);
        response.put("subscriptionServiceImages", _subscriptionService_images);
        response.put("recommendationSubscription", _recommendationSubscription);
        response.put("recommendationCard", _recommendationCard);
        return ResponseEntity.ok(response);
    }
}
