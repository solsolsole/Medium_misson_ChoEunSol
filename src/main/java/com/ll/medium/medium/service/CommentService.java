package com.ll.medium.medium.service;

import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.entity.Comment;
import com.ll.medium.medium.global.user.SiteUser;
import com.ll.medium.medium.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public void create(Board board, String content, SiteUser author) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setBoard(board);
        comment.setAuthor(author);
        commentRepository.save(comment);

    }
}
