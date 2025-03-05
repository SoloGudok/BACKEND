package com.example.backend.domain.image.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.example.backend.domain.image.dto.ImageRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageRes getPresignedUrlToUpload(String fileName) {
        /// 제한시간 설정
        Date expiration = new Date();
        long expTime = expiration.getTime();
        expTime += TimeUnit.MINUTES.toMillis(3); // 3 Minute
        expiration.setTime(expTime);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);

        return ImageRes.builder()
                .url(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString())
                .build();

    }

    public ImageRes getPresignedUrlToDownload(String fileName) {
        /// 제한시간 설정
        Date expiration = new Date();
        long expTime = expiration.getTime();
        expTime += TimeUnit.MINUTES.toMillis(3);
        expiration.setTime(expTime); // 3 Minute

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        return ImageRes.builder()
                .url(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString())
                .build();
    }

}