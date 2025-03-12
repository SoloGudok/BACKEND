package com.example.backend.domain.dashboard.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "DashBoard", description = "대쉬보드 관련 api입니다.")
public class DashBoardController {
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
