package com.dwBoard.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String dateTime;

    // LocalDateTime을 받아서 String 형태로 변환
    public void setDate(LocalDateTime writerDateTime) {
        if (writerDateTime != null) {
            // Timestamp를 String으로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.dateTime = writerDateTime.format(formatter);
        } else {
            this.dateTime = null;  // null 처리 (필요한 경우)
        }
    }
}