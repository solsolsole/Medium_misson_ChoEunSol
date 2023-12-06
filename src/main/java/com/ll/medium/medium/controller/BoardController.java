package com.ll.medium.medium.controller;

import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list (Model model) {
        List<Board> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "board_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board_detail";
    }
}
