package com.bread.bbangpang.post.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ThumbnailService {

    public String extractFirstImageUrl(String htmlContent) {
        // HTML 문자열을 파싱
        Document document = Jsoup.parse(htmlContent);

        // 첫 번째 img 태그를 선택
        Element imgElement = document.selectFirst("img");

        if (imgElement != null) {
            // img 태그의 src 속성을 반환
            return imgElement.attr("src"); 
        } else {
            // img가 없을 경우 기본 이미지를 반환
            return "https://bbangpang.s3.ap-northeast-2.amazonaws.com/thumbnail.png";
        }
    }

}
