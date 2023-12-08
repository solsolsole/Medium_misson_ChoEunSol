package com.ll.medium.medium.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    @NotEmpty(message = "댓글 내용을 작성해주세요.")
    private String content;
}
