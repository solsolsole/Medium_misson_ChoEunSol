package com.ll.medium.medium.controller;

import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.form.BoardForm;
import com.ll.medium.medium.form.CommentForm;
import com.ll.medium.medium.global.user.SiteUser;
import com.ll.medium.medium.global.user.UserService;
import com.ll.medium.medium.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/list") // 게시글 목록
    public String list (Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Board> paging = boardService.getList(page);
        model.addAttribute("paging", paging);
        return "board/board_list";
    }

    @GetMapping(value = "/detail/{id}") // 게시글 상세
    public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/board_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String boardCreate(BoardForm boardForm) {
        return "board/board_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "board/board_form";
        }

        SiteUser siteUser = userService.getUser(principal.getName());
        // 게시글 작성 후 해당 게시글 id 얻어오기.
        Long createdId = Long.valueOf(boardService.create(boardForm.getSubject(), boardForm.getContent(), siteUser));

        // 글 작성 후 리스트가 아닌 작성한 게시글을 확인 할 수 있도록
        // 얻어온 id를 사용하여 해당 게시글의 상세 페이지로 리다이렉트
        return "redirect:/board/detail/" + createdId;
    }

}
