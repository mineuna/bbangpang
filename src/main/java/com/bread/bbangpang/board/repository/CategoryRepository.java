package com.bread.bbangpang.board.repository;

import com.bread.bbangpang.board.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDTO, Long> {
}
