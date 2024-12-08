package com.dwBoard.demo.dto;

import com.dwBoard.demo.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String dateTime;

    // BoardEntity를 받아서 BoardResponseDTO로 변환하는 생성자
    public BoardResponseDTO(BoardEntity boardEntity) {
        if (boardEntity != null) {
            this.id = boardEntity.getId();
            this.title = boardEntity.getTitle();
            this.content = boardEntity.getContent();
            this.writer = boardEntity.getWriter();

            // BoardEntity의 dateTime을 String으로 변환하여 설정
            setDate(boardEntity.getDateTime());
        }
    }

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