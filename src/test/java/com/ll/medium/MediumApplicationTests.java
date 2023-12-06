package com.ll.medium;

import com.ll.medium.medium.entity.Board;
import com.ll.medium.medium.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MediumApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("데이터 베이스에 테스트 내용 추가")
    @Test
    void t1() {
        Board b = new Board();
        b.setSubject("제목 테스트");
        b.setContent("내용 테스트");
        b.setCreateDate(LocalDateTime.now());
        boardRepository.save(b);
    }


}
