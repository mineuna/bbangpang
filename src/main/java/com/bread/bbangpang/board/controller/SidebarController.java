package com.bread.bbangpang.board.controller;

import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sidebar")
public class SidebarController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getSidebar(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "client/sidebar";
    }
}
