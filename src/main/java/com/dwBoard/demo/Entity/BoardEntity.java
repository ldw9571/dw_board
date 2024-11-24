package com.dwBoard.demo.Entity;

// id(long) : index
// title(String) : 제목
// content(String) : 내용
// writerId(String) : 작성자
// writtenTime(Current Time) : 작성시간

import com.dwBoard.demo.dto.BoardRequestDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //null 설정, 최대길이설정
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 300)
    private String content;
    @Column(nullable = false, length = 20)
    private String writer;

    //자동시간설정
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    //dto => entity 변환 함수
    public BoardEntity exchange(BoardRequestDTO boardRequestDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardEntity.getTitle());
        boardEntity.setContent(boardEntity.getContent());
        boardEntity.setWriter(boardEntity.getWriter());

        return boardEntity;
    }

}
