package com.dwBoard.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class BoardRequestDTO {

    private Long index;
    private String title;
    private String content;
    private String writer;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
