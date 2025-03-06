package com.example.backend.domain.image.controller;


import com.example.backend.domain.image.Service.ImageService;
import com.example.backend.domain.image.dto.ImageRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "AWS S3 Presigned URL 관리", description = "AWS S3 파일 관리 컨트롤러")
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "Upload용 Presigned URL 생성", description = "업로드를 위한 Presigned URL을 생성합니다")
    @GetMapping("/presigned/upload")
    public ImageRes getPresignedUrlToUpload(@RequestParam(value = "filename") String fileName) throws IOException {
        return imageService.getPresignedUrlToUpload(fileName);
    }

    @Operation(summary = "Download용 Presigned URL 생성", description = "다운로드를 위한 Presigned URL을 생성합니다")
    @GetMapping("/presigned/download")
    public ImageRes getPresignedUrlToDownload(@RequestParam(value = "filename") String fileName) throws IOException {
        return imageService.getPresignedUrlToDownload(fileName);
    }
}