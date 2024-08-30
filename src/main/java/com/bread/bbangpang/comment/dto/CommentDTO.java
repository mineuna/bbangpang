package com.bread.bbangpang.comment.dto;

import com.bread.bbangpang.post.dto.PostDTO;
import com.bread.bbangpang.report.dto.ReportDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert
@Entity @Table(name = "comment")
public class CommentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentNo;  // 댓글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_no")
    private PostDTO post;  // 게시글

    @JsonIgnore
    @OneToMany(mappedBy = "comment")
    private List<ReportDTO> report;  // 신고

    @CreatedDate
    private LocalDateTime commentRegistered;  // 댓글 작성일
    private String commentContent;  // 댓글 내용
    private String commentWriter;  // 댓글 작성자
    private String commentPassword;  // 댓글 비밀 번호

    @Transient
    private String searchType;  // 댓글 검색 조건
    @Transient
    private String searchKeyword;  // 댓글 검색어

    public Integer getPostNo() {
        return post != null ? post.getPostNo() : null;
    }

}
