package com.bread.bbangpang.inquiry.controller;

import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.service.CategoryService;
import com.bread.bbangpang.inquiry.dto.InquiryDTO;
import com.bread.bbangpang.inquiry.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private CategoryService categoryService;

    // 문의 작성 화면(사용자)
    @GetMapping("inquiry/add")
    public String addInquiryForm(Model model) {
        setSidebar(model);

        return "client/inquiry/addInquiry";
    }

    // 문의 작성(사용자)
    @PostMapping("inquiry/add")
    public String addInquiry(InquiryDTO inquiryDTO, Model model) {
        inquiryService.addInquiry(inquiryDTO);
        setSidebar(model);

        return "redirect:/inquiry/list";
    }

    // 문의 목록(사용자)
    @GetMapping("inquiry/list")
    public String inquiryList(Model model,
                              @PageableDefault(page = 0, size = 15, sort = "inquiryRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                              String searchKeyword, String searchType) {
        Page<InquiryDTO> list = searchInquiry(searchType, searchKeyword, pageable);
        pagingInquiry(model, list);
        setSidebar(model);

        return "client/inquiry/inquiryList";
    }

    // 문의 목록(관리자)
    @GetMapping("Qkdclswk/inquiry/list")
    public String inquiryListAdmin(Model model,
                                   @PageableDefault(page = 0, size = 15, sort = "inquiryRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                                   String searchType, String searchKeyword) {
        Page<InquiryDTO> list = searchInquiry(searchType, searchKeyword, pageable);
        pagingInquiry(model, list);

        return "admin/inquiryList";
    }

    // 문의 내용(사용자)
    @GetMapping("inquiry/{inquiryNo}")
    public String inquiry(@PathVariable("inquiryNo") Integer inquiryNo, Model model) {
        // 조회수
        inquiryService.inquiryViews(inquiryNo);
        // 상세 내용
        model.addAttribute("inquiry", inquiryService.inquiry(inquiryNo));
        setSidebar(model);

        return "client/inquiry/inquiry";
    }

    // 문의글 삭제 비밀 번호 확인 화면(사용자)
    @GetMapping("inquiry/delete/check")
    public String checkInquiryDelete(Model model) {
        setSidebar(model);

        return "client/check/checkInquiryDelete";
    }

    // 문의글 삭제(사용자)
    @PostMapping("inquiry/delete/{inquiryNo}")
    public String deleteInquiry(@RequestParam("inquiryNo") Integer inquiryNo,
                             @RequestParam("inquiryPassword") String inquiryPassword,
                             Model model) {
        // 비밀번호 확인
        boolean isPasswordCorrect = inquiryService.checkPassword(inquiryNo, inquiryPassword);

        if (isPasswordCorrect) {
            // 비밀번호가 일치하면 문의글 삭제
            inquiryService.deleteInquiry(inquiryNo);

            return "redirect:/inquiry/list";
        } else {
            // 비밀번호가 일치하지 않으면 오류 메시지 전달
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");

            return "client/check/checkInquiryDelete";
        }
    }

    // 문의 삭제(관리자)
    @GetMapping("Qkdclswk/inquiry/delete/{inquiryNo}")
    public String deleteInquiryAdmin(@PathVariable("inquiryNo") Integer inquiryNo) {
        inquiryService.deleteInquiry(inquiryNo);

        return "redirect:/Qkdclswk/inquiry/list";
    }

    // 페이징
    private void pagingInquiry(Model model, Page<InquiryDTO> list) {
        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalPage = list.getTotalPages();
        int pageGroup = (nowPage - 1) / 5;
        int startPage = pageGroup * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPage);

        int nextPageGroupStart = Math.min(startPage + 5, totalPage);
        int prevPageGroupStart = Math.max(startPage - 5, 1);

        model.addAttribute("inquiryList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nextPageGroupStart", nextPageGroupStart);
        model.addAttribute("prevPageGroupStart", prevPageGroupStart);
    }

    // 검색
    private Page<InquiryDTO> searchInquiry(String searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null) {
            return inquiryService.inquiryList(pageable);
        } else {
            return switch (searchType) {
                case "inquiryType" -> inquiryService.searchInquiryType(searchKeyword, pageable);
                case "inquiryTitle" -> inquiryService.searchInquiryTitle(searchKeyword, pageable);
                case "inquiryWriter" -> inquiryService.searchInquiryWriter(searchKeyword, pageable);
                default -> inquiryService.inquiryList(pageable);

            };
        }
    }

    // sidebar 설정
    private void setSidebar(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

}