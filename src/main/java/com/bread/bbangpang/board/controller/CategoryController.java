package com.bread.bbangpang.board.controller;

import com.bread.bbangpang.board.dto.BoardDTO;
import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.service.BoardService;
import com.bread.bbangpang.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/QKdclswk/manage")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BoardService boardService;

    @GetMapping
    public String manageCategoriesAndBoards(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        List<BoardDTO> boards = boardService.getAllBoards();
        model.addAttribute("categories", categories);
        model.addAttribute("boards", boards);
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("boardDTO", new BoardDTO());

        return "admin/manage";
    }

    @GetMapping("/category/{categoriesNo}")
    public String filterBoardsByCategory(@PathVariable("categoriesNo") Long categoriesNo, Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        CategoryDTO selectedCategory = categoryService.getCategoryById(categoriesNo);
        List<BoardDTO> boards = boardService.getBoardsByCategory(selectedCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("boards", boards);
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("boardDTO", new BoardDTO());
        model.addAttribute("selectedCategory", selectedCategory);

        return "admin/manage";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);

        return "redirect:/QKdclswk/manage";
    }

    @PostMapping("/board/add")
    public String addBoard(@ModelAttribute BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO);

        return "redirect:/QKdclswk/manage";
    }

    @PostMapping("/category/delete/{categoriesNo}")
    public String delete(@PathVariable Long categoriesNo) {
        categoryService.deleteCategories(Collections.singletonList(categoriesNo));

        return "redirect:/QKdclswk/manage";
    }


}
