package com.bread.bbangpang.notice.controller;

import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.service.CategoryService;
import com.bread.bbangpang.notice.dto.NoticeDTO;
import com.bread.bbangpang.notice.service.NoticeService;
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

import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private CategoryService categoryService;

    // 공지 작성 화면(관리자)
    @GetMapping("Qkdclswk/notice/add")
    public String addNoticeForm(Model model) {
        setSidebar(model);

        return "admin/addNotice";
    }

    // 공지 작성(관리자)
    @PostMapping("Qkdclswk/notice/add")
    public String addNotice(NoticeDTO noticeDTO) {
        noticeService.addNotice(noticeDTO);

        return "redirect:/Qkdclswk/notice/list";
    }

    // 공지 목록(사용자)
    @GetMapping("notice/list")
    public String noticeList(Model model,
                             @PageableDefault(page = 0, size = 15, sort = "noticeRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                             String searchType, String searchKeyword) {
        Page<NoticeDTO> list = searchNotice(searchType, searchKeyword, pageable);
        pagingNotice(model, list);
        setSidebar(model);

        return "client/notice/noticeList";
    }

    // 공지 목록(관리자)
    @GetMapping("Qkdclswk/notice/list")
    public String noticeListAdmin(Model model,
                                  @PageableDefault(page = 0, size = 15, sort = "noticeRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                                  String searchType, String searchKeyword) {
        Page<NoticeDTO> list = searchNotice(searchType, searchKeyword, pageable);
        pagingNotice(model, list);

        return "admin/noticeList";
    }

    // 공지 내용(사용자)
    @GetMapping("notice/{noticeNo}")
    public String notice(@PathVariable("noticeNo") Integer noticeNo, Model model) {
        // 조회수
        noticeService.noticeViews(noticeNo);
        // 상세 내용
        model.addAttribute("notice", noticeService.notice(noticeNo));
        setSidebar(model);

        return "client/notice/notice";
    }

    // 공지 수정 화면(관리자)
    @GetMapping("Qkdclswk/notice/edit/{noticeNo}")
    public String editNoticeForm(@PathVariable("noticeNo") Integer noticeNo, Model model) {
        model.addAttribute("notice", noticeService.notice(noticeNo));

        return "admin/editNotice";
    }

    // 공지 수정(관리자)
    @PostMapping("/notice/edit/{noticeNo}")
    public String editNotice(@PathVariable("noticeNo") Integer noticeNo, NoticeDTO noticeDTO) {
        // 공지 내용 불러오기
        NoticeDTO dto = noticeService.notice(noticeNo);
        // 공지 수정
        dto.setNoticeTitle(noticeDTO.getNoticeTitle());
        dto.setNoticeContent(noticeDTO.getNoticeContent());
        // 공지 등록
        noticeService.addNotice(dto);

        return "redirect:/Qkdclswk/notice/list";
    }

    // 공지 삭제(관리자)
    @GetMapping("Qkdclswk/notice/delete/{noticeNo}")
    public String deleteNotice(@PathVariable("noticeNo") Integer noticeNo) {
        noticeService.deleteNotice(noticeNo);

        return "redirect:/Qkdclswk/notice/list";
    }

    // 페이징
    private void pagingNotice(Model model, Page<NoticeDTO> list) {
        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalPage = list.getTotalPages();
        int pageGroup = (nowPage - 1) / 5;
        int startPage = pageGroup * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPage);

        int nextPageGroupStart = Math.min(startPage + 5, totalPage);
        int prevPageGroupStart = Math.max(startPage - 5, 1);

        model.addAttribute("noticeList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nextPageGroupStart", nextPageGroupStart);
        model.addAttribute("prevPageGroupStart", prevPageGroupStart);
    }

    // 검색
    private Page<NoticeDTO> searchNotice(String searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null) {
            return noticeService.noticeList(pageable);
        } else {
            return switch (searchType) {
                case "noticeTitle" -> noticeService.searchNoticeTitle(searchKeyword, pageable);
                case "noticeContent" -> noticeService.searchNoticeContent(searchKeyword, pageable);
                default ->  noticeService.noticeList(pageable);
            };
        }
    }

    // sidebar 설정
    private void setSidebar(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

}
