package com.bread.bbangpang.report.controller;

import com.bread.bbangpang.comment.dto.CommentDTO;
import com.bread.bbangpang.comment.service.CommentService;
import com.bread.bbangpang.post.dto.PostDTO;
import com.bread.bbangpang.post.service.PostService;
import com.bread.bbangpang.report.dto.ReportDTO;
import com.bread.bbangpang.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    // 게시글 신고 화면(사용자)
    @GetMapping("report/add/post")
    public String addReportForm(Model model) {
        return "client/report/addPostReport";
    }

    // 게시글 신고(사용자)
    @PostMapping("report/add/post/{postNo}")
    @ResponseBody
    public String addPostReport(@ModelAttribute ReportDTO reportDTO,
                                @PathVariable(value = "postNo") Integer postNo,
                                @RequestParam("reportReason") String reportReason) {
        PostDTO post = postService.post(postNo);
        reportDTO.setPost(post);
        reportDTO.setReportReason(reportReason);
        reportDTO.setReportRegistered(LocalDateTime.now());

        reportService.addReport(reportDTO);

        return "<script>alert('신고가 접수되었습니다.'); window.close();</script>";
    }

    // 댓글 신고 화면(사용자)
    @GetMapping("report/add/comment")
    public String addCommentReportForm(@RequestParam("postNo") Integer postNo,
                                       @RequestParam("commentNo") Integer commentNo,
                                       Model model) {
        model.addAttribute("postNo", postNo);
        model.addAttribute("commentNo", commentNo);

        return "client/report/addCommentReport";
    }

    // 댓글 신고(사용자)
    @PostMapping("post/{postNo}/comments/report/{commentNo}")
    @ResponseBody
    public String addCommentReport(@ModelAttribute ReportDTO reportDTO,
                                   @PathVariable("commentNo") Integer commentNo,
                                   @PathVariable("postNo") Integer postNo,
                                   @RequestParam("reportReason") String reportReason) {
        CommentDTO comment = commentService.comment(commentNo);
        reportDTO.setComment(comment);
        reportDTO.setReportReason(reportReason);
        reportDTO.setReportRegistered(LocalDateTime.now());

        reportService.addReport(reportDTO);

        return "<script>alert('신고가 접수되었습니다.'); window.close();</script>";
    }

    // 신고 목록(관리자)
    @GetMapping("Qkdclswk/report/list")
    public String reportList(Model model,
                             @PageableDefault(page = 0, size = 15, sort = "reportRegistered", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ReportDTO> list = reportService.reportList(pageable);

        // 페이징
        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalPage = list.getTotalPages();
        int pageGroup = (nowPage - 1) / 5;
        int startPage = pageGroup * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPage);

        int nextPageGroupStart = Math.min(startPage + 5, totalPage);
        int prevPageGroupStart = Math.max(startPage - 5, 1);

        model.addAttribute("reportList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nextPageGroupStart", nextPageGroupStart);
        model.addAttribute("prevPageGroupStart", prevPageGroupStart);

        return "admin/reportList";
    }

}
