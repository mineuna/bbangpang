package com.bread.bbangpang.comment.controller;

import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.service.CategoryService;
import com.bread.bbangpang.comment.dto.CommentDTO;
import com.bread.bbangpang.comment.service.CommentService;
import com.bread.bbangpang.post.dto.PostDTO;
import com.bread.bbangpang.post.repositiory.PostRepository;
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
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryService categoryService;

    // 댓글 등록(사용자)
    @PostMapping("post/{postNo}/comments")
    public String saveNewComment(@PathVariable("postNo") Integer postNo,
                                 @RequestParam("commentWriter") String commentWriter,
                                 @RequestParam("commentContent") String commentContent,
                                 @RequestParam("commentPassword") String commentPassword) {
        PostDTO post = postRepository.findById(postNo).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postNo));

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPost(post);
        commentDTO.setCommentWriter(commentWriter);
        commentDTO.setCommentContent(commentContent);
        commentDTO.setCommentPassword(commentPassword);

        commentService.saveComment(commentDTO);

        return "redirect:/post/" + postNo;
    }

    // 댓글 삭제 비밀 번호 확인 화면(사용자)
    @GetMapping("comment/delete/check")
    public String checkCommentDelete(@RequestParam("postNo") Integer postNo,
                                     @RequestParam("commentNo") Integer commentNo,
                                     Model model) {
        // sidebar
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("postNo", postNo);
        model.addAttribute("commentNo", commentNo);

        return "client/check/checkCommentDelete";
    }

    // 댓글 삭제(사용자)
    @PostMapping("post/{postNo}/comments/delete/{commentNo}")
    public String deleteComment(@PathVariable("commentNo") Integer commentNo,
                                @PathVariable("postNo") Integer postNo,
                                @RequestParam("commentPassword") String commentPassword,
                                Model model) {
        // 비밀번호 확인
        boolean isPasswordCorrect = commentService.checkPassword(commentNo, commentPassword);

        if (isPasswordCorrect) {
            // 비밀번호가 일치하면 댓글 삭제
            commentService.deleteCommentById(commentNo);

            return "redirect:/post/" + postNo;
        } else {
            // 비밀번호가 일치하지 않으면 오류 메시지 전달
            model.addAttribute("postNo", postNo);
            model.addAttribute("commentNo", commentNo);
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");

            return "client/check/checkCommentDelete";
        }
    }

    // 댓글 목록(관리자)
    @GetMapping("Qkdclswk/comment/list")
    public String commentAdminList(Model model,
                                   @PageableDefault(page = 0, size = 15, sort = "commentRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                                   String searchKeyword, String searchType) {
        Page<CommentDTO> list = searchComment(searchType, searchKeyword, pageable);
        pagingComment(model, list);

        return "admin/commentList";
    }

    // 댓글 삭제(관리자)
    @GetMapping("Qkdclswk/comment/delete/{commentNo}")
    public String deleteCommentAdmin(@PathVariable("commentNo") Integer commentNo) {
        commentService.deleteCommentById(commentNo);

        return "redirect:/Qkdclswk/comment/list";
    }

    // 페이징
    private void pagingComment(Model model, Page<CommentDTO> list) {
        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalPage = list.getTotalPages();
        int pageGroup = (nowPage - 1) / 5;
        int startPage = pageGroup * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPage);

        int nextPageGroupStart = Math.min(startPage + 5, totalPage);
        int prevPageGroupStart = Math.max(startPage - 5, 1);

        model.addAttribute("commentList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nextPageGroupStart", nextPageGroupStart);
        model.addAttribute("prevPageGroupStart", prevPageGroupStart);
    }

    // 검색
    private Page<CommentDTO> searchComment(String searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null) {
            return commentService.commentList(pageable);
        } else {
            return switch (searchType) {
                case "commentContent" -> commentService.searchCommentContent(searchKeyword, pageable);
                case "commentWriter" -> commentService.searchCommentWriter(searchKeyword, pageable);
                default -> commentService.commentList(pageable);
            };
        }
    }

}
