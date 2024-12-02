package com.dwBoard.demo;

import com.dwBoard.demo.entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.repository.BoardRepository;
import com.dwBoard.demo.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void save(){
        //given
        BoardRequestDTO dto = new BoardRequestDTO();
        dto.setTitle("제목");
        dto.setContent("내용");
        dto.setWriter("작성자");

        //when
        BoardEntity write = boardService.write(dto);

        //then
        Long id = write.getId();
        System.out.println("id = " + id);

    }
}
