package com.bread.bbangpang.post.dto;

import com.bread.bbangpang.board.dto.BoardDTO;
import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.report.dto.ReportDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Entity @Table(name ="post")
public class PostDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postNo;  // 게시글 번호

    @ManyToOne
    @JoinColumn(name = "category_no")
    private CategoryDTO category;  // 카테고리

    @ManyToOne
    @JoinColumn(name = "board_no")
    private BoardDTO board;  // 게시판

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<ReportDTO> report;

    private String postTitle;  // 게시글 제목
    private String postContent;  // 게시글 내용
    private String postThumbnail;  // 게시글 썸네일
    @CreatedDate
    private LocalDateTime postRegistered;  // 게시글 작성일
    private String postWriter;  // 게시글 작성자
    private String postPassword;  // 게시글 비밀 번호
    private Integer postViews;  // 게시글 조회수
    @LastModifiedDate
    private LocalDateTime postUpdated;  // 게시글 수정일

    @Transient
    private String categoriesName;  // 카테고리 이름
    @Transient
    private String boardName;  // 게시판 이름

    @Transient
    private Long categoryNo;
    @Transient
    private Integer boardNo;

    @Transient
    private String searchType;  // 게시글 검색 조건
    @Transient
    private String searchKeyword;  // 게시글 검색어

    public PostDTO(PostDTO post) {
        this.postNo = post.getPostNo();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postWriter = post.getPostWriter();
        this.postViews = post.getPostViews();
        this.postRegistered = post.getPostRegistered();
        this.postUpdated = post.getPostUpdated();
        this.board = post.getBoard();
    }

}
