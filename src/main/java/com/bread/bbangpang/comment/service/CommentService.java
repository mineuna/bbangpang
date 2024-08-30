package com.bread.bbangpang.comment.service;

import com.bread.bbangpang.comment.dto.CommentDTO;
import com.bread.bbangpang.comment.repository.CommentRepository;
import com.bread.bbangpang.post.dto.PostDTO;
import com.bread.bbangpang.post.repositiory.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<CommentDTO> findCommentsByPost(Integer postNo) {
        return commentRepository.findByPost_PostNo(postNo);
    }

    public CommentDTO comment(Integer commentNo) {
        return commentRepository.findById(commentNo).get();
    }

    public void saveComment(CommentDTO commentDTO) {
        PostDTO postDTO = postRepository.findById(commentDTO.getPostNo()).orElseThrow();

        CommentDTO comment = new CommentDTO();
        comment.setPost(postDTO);
        comment.setCommentContent(commentDTO.getCommentContent());
        comment.setCommentPassword(commentDTO.getCommentPassword());
        comment.setCommentWriter(commentDTO.getCommentWriter());

        commentRepository.save(comment);
    }

    public boolean checkPassword(Integer commentNo, String commentPassword) {
        CommentDTO comment = commentRepository.findById(commentNo).orElse(null);
        // 비밀번호 확인
        if (comment != null) {
            return comment.getCommentPassword().equals(commentPassword);
        }
        return false;
    }

    public void deleteCommentById(Integer commentNo) {
        commentRepository.deleteById(commentNo);
    }

    public Page<CommentDTO> commentList(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Page<CommentDTO> searchCommentContent(String searchKeyword, Pageable pageable) {
        return commentRepository.findByCommentContentContaining(searchKeyword, pageable);
    }
    public Page<CommentDTO> searchCommentWriter(String searchKeyword, Pageable pageable) {
        return commentRepository.findByCommentWriterContaining(searchKeyword, pageable);
    }

}
