package com.bread.bbangpang.board.service;

import com.bread.bbangpang.board.dto.BoardDTO;
import com.bread.bbangpang.board.dto.CategoryDTO;
import com.bread.bbangpang.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll();
    }

    public List<BoardDTO> getBoardsByCategory(CategoryDTO categoryDTO) {
        return boardRepository.findByCategoryDTO(categoryDTO);
    }

    public void saveBoard(BoardDTO boardDTO) {
        if (boardDTO.getBoardNo() == 0) {
            boardDTO.setBoardRegistered(LocalDateTime.now());
        }

        boardRepository.save(boardDTO);
    }

    public BoardDTO getBoardById(int boardNo) {
        return boardRepository.findById((long) boardNo).orElse(null);
    }

    public void deleteBoards(List<Long> boardIds) {
        if (boardIds != null && !boardIds.isEmpty()) {
            boardRepository.deleteAllById(boardIds);
        }
    }


}
