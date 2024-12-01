package com.dwBoard.demo.service;

import com.dwBoard.demo.Entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    //게시글작성
    public BoardEntity write(BoardRequestDTO boardRequestDTO){

        //DTO => ENTITY
        BoardEntity dtoExchangeEntity= new BoardEntity();
        BoardEntity exchangeDtoToEntity = dtoExchangeEntity.exchange(boardRequestDTO);


        BoardEntity boardEntity = boardRepository.save(exchangeDtoToEntity);
        return boardEntity;
    }

    public Optional<BoardEntity> findById(Long id) {
        Optional<BoardEntity> board = boardRepository.findById(id);
        return board;
    }
}
