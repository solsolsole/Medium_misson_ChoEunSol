package com.ll.medium;

import com.ll.medium.medium.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MediumApplicationTests {

    @Autowired
    private BoardService boardService;

    @DisplayName("테스트 데이터 추가")
    @Test
    void t1 () {
        for (int i = 0; i < 25; i++) {
            String subject = String.format("test%03d",i);
            String content = "test";
            boardService.create(subject, content, null);
        }
    }


}
