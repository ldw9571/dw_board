package com.dwBoard.demo;

import com.dwBoard.demo.repository.BoardRepository;
import com.dwBoard.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final BoardRepository boardRepository;

    @Autowired
    public SpringConfig(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardService boardService(){
        return new BoardService(boardRepository);
    }

}
