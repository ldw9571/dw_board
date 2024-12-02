package com.dwBoard.demo.entity;

import com.dwBoard.demo.dto.BoardRequestDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 300)
    private String content;

    @Column(nullable = false, length = 20)
    private String writer;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime dateTime;


    // dto => entity 변환 함수
    public BoardEntity exchange(BoardRequestDTO boardRequestDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardRequestDTO.getTitle());
        boardEntity.setContent(boardRequestDTO.getContent());
        boardEntity.setWriter(boardRequestDTO.getWriter());
        boardEntity.setDateTime(LocalDateTime.now());

        return boardEntity;
    }
}
