package com.bread.bbangpang.board.repository;

import com.bread.bbangpang.board.dto.BoardDTO;
import com.bread.bbangpang.board.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, Long> {

    List<BoardDTO> findAll();

    List<BoardDTO> findByCategoryDTO(CategoryDTO categoryDTO);

}
