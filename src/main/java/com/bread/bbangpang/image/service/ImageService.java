package com.bread.bbangpang.image.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bread.bbangpang.config.S3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private S3Config s3Config;

    @Autowired
    public ImageService(S3Config s3Config) {
        this.s3Config = s3Config;
    }

    @Value("${cloud.aws.s3.bucketname}")
    private String bucket;

    public String uploadImage(MultipartRequest request) throws IOException {
        // request에서 파일을 가져옴
        MultipartFile file = request.getFile("file");

        if (file == null) {
            throw new IllegalArgumentException("File must not be null");
        }

        // 파일명과 확장자 추출
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("File name must not be null");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String savedName = UUID.randomUUID() + extension;

        // S3에 파일 업로드
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, savedName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        // 업로드된 파일의 URL 생성
        String imageUrl = s3Config.amazonS3Client().getUrl(bucket, savedName).toString();

        return imageUrl;
    }


}