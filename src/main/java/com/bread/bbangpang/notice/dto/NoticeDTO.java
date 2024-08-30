package com.bread.bbangpang.notice.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Entity @Table(name = "notice")
public class NoticeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeNo;  // 공지 번호

    private String noticeTitle;  // 공지 제목
    private String noticeContent;  // 공지 내용
    @CreatedDate
    private LocalDateTime noticeRegistered;  // 공지 작성일
    private Integer noticeViews;  // 공지 조회수
    @LastModifiedDate
    private LocalDateTime noticeUpdated;  // 공지 수정일

    @Transient
    private String searchType;  // 공지 검색 조건
    @Transient
    private String searchKeyword;  // 공지 검색어

}