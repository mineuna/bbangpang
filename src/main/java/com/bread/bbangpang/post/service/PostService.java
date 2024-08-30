package com.bread.bbangpang.post.service;

import com.bread.bbangpang.post.dto.PostDTO;
import com.bread.bbangpang.post.repositiory.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ThumbnailService contentService;

    public void addPost(PostDTO postDTO) {
        PostDTO post = new PostDTO();
        post.setCategory(postDTO.getCategory());
        post.setBoard(postDTO.getBoard());
        post.setPostTitle(postDTO.getPostTitle());
        post.setPostContent(postDTO.getPostContent());
        post.setPostWriter(postDTO.getPostWriter());
        post.setPostPassword(postDTO.getPostPassword());
        post.setPostRegistered(LocalDateTime.now());;

        postRepository.save(post);
    }

    public void updatePost(PostDTO postDTO) {
        postDTO.setPostUpdated(LocalDateTime.now());

        postRepository.save(postDTO);
    }

    public Page<PostDTO> postList(Pageable pageable) {
        return getThumbnail(postRepository.findAll(pageable));
    }

    public Page<PostDTO> searchPostTitle(String searchKeyword, Pageable pageable) {
        return getThumbnail(postRepository.findByPostTitleContaining(searchKeyword, pageable));
    }

    public Page<PostDTO> searchPostContent(String searchKeyword, Pageable pageable) {
        return getThumbnail(postRepository.findByPostContentContaining(searchKeyword, pageable));
    }

    public Page<PostDTO> searchPostWriter(String searchKeyword, Pageable pageable) {
        return getThumbnail(postRepository.findByPostWriterContaining(searchKeyword, pageable));
    }

    public Page<PostDTO> findByCategory(Long categoriesNo, Pageable pageable) {
        return getThumbnail(postRepository.findByCategory_CategoriesNo(categoriesNo, pageable));
    }

    public Page<PostDTO> findByBoard(Integer boardNo, Pageable pageable) {
        return getThumbnail(postRepository.findByBoard_BoardNo(boardNo, pageable));
    }

    @Transactional
    public int postViews(Integer postNo) {
        return postRepository.updateViews(postNo);
    }

    public PostDTO post(Integer postNo) {
        return postRepository.findById(postNo).get();
    }

    public boolean checkPassword(Integer postNo, String postPassword) {
        PostDTO post = postRepository.findById(postNo).orElseThrow(() -> new RuntimeException("Post not found"));
        // 비밀번호 확인
        return post.getPostPassword().equals(postPassword);
    }

    public void deletePost(Integer postNo) {
        postRepository.deleteById(postNo);
    }

    private Page<PostDTO> getThumbnail(Page<PostDTO> posts) {
        return posts.map(post -> {
            PostDTO dto = new PostDTO(post);
            dto.setPostThumbnail(contentService.extractFirstImageUrl(post.getPostContent()));
            return dto;
        });
    }

}
