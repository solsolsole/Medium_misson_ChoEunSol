package com.ll.medium.medium.controller;

import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.form.CommentForm;
import com.ll.medium.medium.global.user.SiteUser;
import com.ll.medium.medium.global.user.UserService;
import com.ll.medium.medium.service.BoardService;
import com.ll.medium.medium.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}") // 댓글 등록
    public String createComment(Model model, @PathVariable("id") Integer id, @Valid CommentForm commentForm,
                                BindingResult bindingResult, Principal principal) {
        Board board = boardService.getBoard(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", board);
            return "board/board_detail";
        }
        commentService.create(board, commentForm.getContent(), siteUser);
        return String.format("redirect:/board/detail/%s", id);
    }
}
