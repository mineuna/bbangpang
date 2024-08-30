package com.bread.bbangpang.post.controller;

import com.bread.bbangpang.board.dto.BoardDTO;
import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.service.BoardService;
import com.bread.bbangpang.board.service.CategoryService;
import com.bread.bbangpang.comment.dto.CommentDTO;
import com.bread.bbangpang.comment.service.CommentService;
import com.bread.bbangpang.post.dto.PostDTO;
import com.bread.bbangpang.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    // 게시글 작성 화면(사용자)
    @GetMapping("post/add")
    public String addPostForm(Model model) {
        setSidebar(model);

        return "client/post/addPost";
    }

    // 게시글 작성(사용자)
    @PostMapping("post/add")
    public String addPost(@ModelAttribute PostDTO postDTO) {
        CategoryDTO category = categoryService.getCategoryById(postDTO.getCategoryNo());
        postDTO.setCategory(category);
        BoardDTO board = boardService.getBoardById(postDTO.getBoardNo());
        postDTO.setBoard(board);
        postService.addPost(postDTO);

        return "redirect:/post/list";
    }

    // 인기 게시글 목록(사용자)
    @GetMapping("home")
    public String topList(Model model,
                          @PageableDefault(page=0, size = 15, sort = "postViews", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostDTO> list = postService.postList(pageable);
        pagingPost(model, list);
        setSidebar(model);

        return "client/post/topList";
    }

    // 게시글 목록(사용자)
    @GetMapping("post/list")
    public String postList(Model model,
                           @RequestParam(value = "categoriesNo", required = false) Long categoriesNo,
                           @RequestParam(value = "boardNo", required = false) Integer boardNo,
                           @PageableDefault(page = 0, size = 15, sort = "postRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                           String searchType, String searchKeyword) {
        if (categoriesNo != null) {
            CategoryDTO category = categoryService.getCategoryById(categoriesNo);
            model.addAttribute("category", category);
        }

        if (boardNo != null) {
            BoardDTO board = boardService.getBoardById(boardNo);
            model.addAttribute("board", board);
        }

        if(categoriesNo == null && boardNo == null ) {
            Page<PostDTO> list = searchPost(searchType, searchKeyword, pageable);
            pagingPost(model, list);
        } else if (categoriesNo != null && boardNo == null) {
            Page<PostDTO> list = postService.findByCategory(categoriesNo, pageable);
            pagingPost(model, list);
        } else {
            Page<PostDTO> list = postService.findByBoard(boardNo, pageable);
            pagingPost(model, list);
        }

        setSidebar(model);

        return "client/post/postList";
    }

    // 게시글 목록(관리자)
    @GetMapping("Qkdclswk/post/list")
    public String postListAdmin(Model model,
                                @PageableDefault(page = 0, size = 15, sort = "postRegistered", direction = Sort.Direction.DESC) Pageable pageable,
                                String searchKeyword, String searchType) {
        Page<PostDTO> list = searchPost(searchType, searchKeyword, pageable);
        pagingPost(model, list);

        return "admin/postList";
    }

    // 게시글 내용(사용자)
    @GetMapping("post/{postNo}")
    public String post(@PathVariable("postNo") Integer postNo, Model model) {
        // 조회수
        postService.postViews(postNo);
        //댓글
        List<CommentDTO> comments = commentService.findCommentsByPost(postNo);
        // 상세 내용
        model.addAttribute("post", postService.post(postNo));
        //댓글
        model.addAttribute("comments", comments);
        setSidebar(model);

        return "client/post/post";
    }

    // 게시글 수정 비밀 번호 확인 화면(사용자)
    @GetMapping("post/edit/check")
    public String checkPostEdit(Model model) {
        setSidebar(model);

        return "client/check/checkPostEdit";
    }

    // 게시글 수정 화면(사용자)
    @GetMapping("post/edit/{postNo}")
    public String editPostForm(@PathVariable("postNo") Integer postNo,
                               @RequestParam("postPassword") String postPassword,
                               Model model) {
        model.addAttribute("post", postService.post(postNo));
        setSidebar(model);
       // 비밀번호 확인
        boolean isPasswordCorrect = postService.checkPassword(postNo, postPassword);

        if (isPasswordCorrect) {
            return "client/post/editPost";
        } else {
            // 비밀번호가 일치하지 않으면 오류 메시지 전달
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");

            return "client/check/checkPostEdit";
        }
    }

    // 게시글 수정(사용자)
    @PostMapping("post/edit/{postNo}")
    public String editPost(@PathVariable("postNo") Integer postNo, @ModelAttribute PostDTO postDTO, Model model) {
        // 게시글 내용 불러오기
        PostDTO dto = postService.post(postNo);
        CategoryDTO category = categoryService.getCategoryById(postDTO.getCategoryNo());
        BoardDTO board = boardService.getBoardById(postDTO.getBoardNo());
        // 게시글 수정
        dto.setCategory(category);
        dto.setBoard(board);
        dto.setPostTitle(postDTO.getPostTitle());
        dto.setPostContent(postDTO.getPostContent());
        dto.setPostWriter(postDTO.getPostWriter());
        dto.setPostPassword(postDTO.getPostPassword());
        // 게시글 등록
        postService.updatePost(dto);

        return "redirect:/post/{postNo}";
    }

    // 게시글 삭제 비밀 번호 확인 화면(사용자)
    @GetMapping("post/delete/check")
    public String checkPostDelete(Model model) {
        setSidebar(model);

        return "client/check/checkPostDelete";
    }

    // 게시글 삭제(사용자)
    @PostMapping("post/delete/{postNo}")
    public String deletePost(@RequestParam("postNo") Integer postNo,
                             @RequestParam("postPassword") String postPassword,
                             Model model) {
        // 비밀번호 확인
        boolean isPasswordCorrect = postService.checkPassword(postNo, postPassword);

        if (isPasswordCorrect) {
            // 비밀번호가 일치하면 게시글 삭제
            postService.deletePost(postNo);
            return "redirect:/post/list";
        } else {
            // 비밀번호가 일치하지 않으면 오류 메시지 전달
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "client/check/checkPostDelete";
        }
    }

    // 게시글 삭제(관리자)
    @GetMapping("Qkdclswk/post/delete/{postNo}")
    public String deletePostAdmin(@PathVariable("postNo") Integer postNo) {
        postService.deletePost(postNo);

        return "redirect:/Qkdclswk/post/list";
    }

    // 페이징
    private void pagingPost(Model model, Page<PostDTO> list) {
        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalPage = list.getTotalPages();
        int pageGroup = (nowPage - 1) / 5;
        int startPage = pageGroup * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPage);

        int nextPageGroupStart = Math.min(startPage + 5, totalPage);
        int prevPageGroupStart = Math.max(startPage - 5, 1);

        model.addAttribute("postList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nextPageGroupStart", nextPageGroupStart);
        model.addAttribute("prevPageGroupStart", prevPageGroupStart);
    }

    // 검색
    private Page<PostDTO> searchPost(String searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null) {
            return postService.postList(pageable);
        } else {
            return switch (searchType) {
                case "postTitle" -> postService.searchPostTitle(searchKeyword, pageable);
                case "postContent" -> postService.searchPostContent(searchKeyword, pageable);
                case "postWriter" -> postService.searchPostWriter(searchKeyword, pageable);
                default -> postService.postList(pageable);
            };
        }
    }

    // sidebar 설정
    private void setSidebar(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

}
