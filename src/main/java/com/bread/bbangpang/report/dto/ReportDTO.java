package com.bread.bbangpang.report.dto;

import com.bread.bbangpang.comment.dto.CommentDTO;
import com.bread.bbangpang.post.dto.PostDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert
@Entity @Table(name = "report")
public class ReportDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportNo;  // 신고 번호

    @ManyToOne
    @JoinColumn(name = "post_no")
    private PostDTO post;  // 게시글

    @ManyToOne
    @JoinColumn(name = "comment_no")
    private CommentDTO comment;  // 댓글

    private String reportReason;  // 신고 사유
    @CreatedDate
    private LocalDateTime reportRegistered;  // 신고일

    @Transient
    private Integer postNo;  // 게시글 번호
    @Transient
    private Integer commentNo;  // 댓글 번호

    @Transient
    private String postTitle;  // 게시글 제목
    @Transient
    private String commentContent;  // 댓글 내용

}
