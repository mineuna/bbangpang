package com.bread.bbangpang.board.dto;

import com.bread.bbangpang.post.dto.PostDTO;
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
@Entity @Table(name = "board")
public class BoardDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNo;  // 게시판 번호

    @ManyToOne
    @JoinColumn(name = "category_no")
    private CategoryDTO categoryDTO;  // 카테고리

    @JsonIgnore
    @OneToMany(mappedBy = "board")
    private List<PostDTO> post;  // 게시글

    private String boardName;  // 게시판 이름
    private String boardDescription;  // 게시판 설명
    @CreatedDate
    private LocalDateTime boardRegistered;  // 게시판 생성일

}
