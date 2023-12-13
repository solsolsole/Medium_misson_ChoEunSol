package com.ll.medium.medium.service;

import com.ll.medium.DataNotFoundException;
import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.global.user.SiteUser;
import com.ll.medium.medium.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Integer create(String subject, String content, SiteUser user) {
        Board b = new Board();
        b.setSubject(subject);
        b.setContent(content);
        b.setCreateDate(LocalDateTime.now());
        b.setAuthor(user);

        // 생성된 게시글 id 반환
        Board savedBoard = boardRepository.save(b);
        return savedBoard.getId();
    }

    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
        return boardRepository.findAll(pageable);
    }

    public void modify (Board board, String subject, String content) {
        board.setSubject(subject);
        board.setContent(content);
        board.setModifyDate(LocalDateTime.now());
        boardRepository.save(board);
    }

    public void delete (Board board) {
        boardRepository.delete(board);
    }
}
