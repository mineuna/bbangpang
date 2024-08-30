package com.bread.bbangpang.board.controller;

import com.bread.bbangpang.board.dto.BoardDTO;
import com.bread.bbangpang.board.service.BoardService;
import com.bread.bbangpang.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/QKdclswk/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String getAllBoards(Model model) {
        List<BoardDTO> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/add")
    public String addBoardForm(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "board/add";
    }

    @PostMapping("/add")
    public String addBoard(@ModelAttribute BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO);

        return "redirect:/board/list";
    }

    @GetMapping("/delete/{boardNo}")
    public String deleteBoard(@PathVariable int boardNo) {
        List<Long> boardIds = Collections.singletonList((long) boardNo);
        boardService.deleteBoards(boardIds);
        return "redirect:/board/list";
    }



}
