package com.bread.bbangpang.post.repositiory;

import com.bread.bbangpang.post.dto.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostDTO, Integer> {

    @Modifying
    @Query("UPDATE PostDTO p SET p.postViews = p.postViews + 1 where p.postNo = :postNo")
    int updateViews(@Param("postNo") Integer postNo);

    Page<PostDTO> findByPostTitleContaining(String searchKeyword, Pageable pageable);
    Page<PostDTO> findByPostContentContaining(String searchKeyword, Pageable pageable);
    Page<PostDTO> findByPostWriterContaining(String searchKeyword, Pageable pageable);

    Page<PostDTO> findByBoard_BoardNo(Integer boardNo, Pageable pageable);
    Page<PostDTO> findByCategory_CategoriesNo(Long categoriesNo, Pageable pageable);

}
