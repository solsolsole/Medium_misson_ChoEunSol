package com.ll.medium.medium.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardForm {
    @NotEmpty(message = "제목을 작성해주세요.")
    @Size(max = 200)
    private String subject;

    @NotEmpty(message = "내용을 작성해주세요.")
    private String content;
}
