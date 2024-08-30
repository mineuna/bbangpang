package com.bread.bbangpang.board.dto;

import com.bread.bbangpang.post.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert
@Entity @Table(name = "categories")
public class CategoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriesNo;  // 카테고리 번호

    @OneToMany(mappedBy = "categoryDTO", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardDTO> boards;  // 게시판

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<PostDTO> post;  // 게시글

    private String categoriesName;  // 카테고리 이름
    private String categoriesDescription;  // 카테고리 설명
    @CreatedDate
    private LocalDateTime categoriesRegistered;  // 카테고리 생성일

}
