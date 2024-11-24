package com.dwBoard.demo.Entity;

// id(long) : index
// title(String) : 제목
// content(String) : 내용
// writerId(String) : 작성자
// writtenTime(Current Time) : 작성시간

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class BoardEntity {

    @Id
    private Long index;

    //null 설정, 최대길이설정
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 300)
    private String content;
    @Column(nullable = false, length = 20)
    private String writerId;

    //자동시간설정
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime dateTime;

}
