package com.ll.medium.medium.service;

import com.ll.medium.DataNotFoundException;
import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    public Board getBoard(Integer id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            throw new DataNotFoundException("게시글이 존재 하지 않습니다.");
        }
    }
}
