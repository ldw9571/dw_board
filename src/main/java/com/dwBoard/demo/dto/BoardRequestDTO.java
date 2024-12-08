package com.dwBoard.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardRequestDTO {

    private Long index;

    @NotNull(message = "제목 입력 후 재등록해주세요.")
    @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하로 작성해야 합니다.")
    private String title;

    @NotNull(message = "내용 입력 후 재등록해주세요.")
    @Size(min = 1, message = "내용은 최소 1자 이상 작성해야 합니다.")
    private String content;

    @NotNull(message = "작성자 입력 후 재등록해주세요.")
    @Size(min = 1, max = 50, message = "작성자는 1자 이상 50자 이하로 작성해야 합니다.")
    private String writer;
}
