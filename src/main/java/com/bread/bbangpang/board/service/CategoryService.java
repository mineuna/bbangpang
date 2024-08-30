package com.bread.bbangpang.board.service;

import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getCategoriesNo() == null) {
            categoryDTO.setCategoriesRegistered(LocalDateTime.now());
        }
        categoryRepository.save(categoryDTO);
    }

    public CategoryDTO getCategoryById(Long categoriesNo) {
        return categoryRepository.findById(categoriesNo).orElse(null);
    }

    public void deleteCategories(List<Long> categoryIds) {
        if(categoryIds != null && !categoryIds.isEmpty()) {
            categoryRepository.deleteAllById(categoryIds);
        }
    }

}
