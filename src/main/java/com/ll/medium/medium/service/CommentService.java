package com.ll.medium.medium.service;

import com.ll.medium.DataNotFoundException;
import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.entity.Comment;
import com.ll.medium.medium.global.user.SiteUser;
import com.ll.medium.medium.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Comment getComment (Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new DataNotFoundException("댓글이 존재하지 않습니다.");
        }
    }

    public void modify (Comment comment, String content) {
        comment.setContent(content);
        comment.setModifyDate(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void  delete (Comment comment) {
        commentRepository.delete(comment);
    }

    public void vote(Comment comment, SiteUser siteUser) {
        comment.getVoter().add(siteUser);
        commentRepository.save(comment);
    }
}
