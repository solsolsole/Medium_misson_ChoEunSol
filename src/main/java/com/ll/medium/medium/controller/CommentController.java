package com.ll.medium.medium.controller;

import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.form.CommentForm;
import com.ll.medium.medium.service.BoardService;
import com.ll.medium.medium.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/create/{id}") // 댓글 등록
    public String createComment(Model model, @PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult bindingResult) {
        Board board = boardService.getBoard(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", board);
            return "board/board_detail";
        }
        commentService.create(board, commentForm.getContent()); // 댓글 저장
        return String.format("redirect:/board/detail/%s", id);
    }
}
