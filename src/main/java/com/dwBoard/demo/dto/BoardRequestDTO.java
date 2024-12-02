package com.dwBoard.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardRequestDTO {

    private Long index;
    private String title;
    private String content;
    private String writer;


}
