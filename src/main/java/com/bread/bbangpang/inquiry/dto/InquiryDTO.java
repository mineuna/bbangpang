package com.bread.bbangpang.inquiry.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert
@Entity @Table(name = "inquiry")
public class InquiryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inquiryNo;  // 문의 번호

    private String inquiryType;  // 문의 타입
    private String inquiryTitle;  // 문의 제목
    private String inquiryContent;  // 문의 내용
    @CreatedDate
    private LocalDateTime inquiryRegistered;  // 문의 작성일
    private String inquiryWriter;  // 문의 작성자
    private String inquiryPassword;  // 문의 비밀 번호
    private Integer inquiryViews;  // 문의 조회수

    @Transient
    private String searchType;  // 문의 검색 조건
    @Transient
    private String searchKeyword;  // 문의 검색어

}
