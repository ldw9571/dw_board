package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    BoardEntity save(BoardEntity boardRequestDTO);
    //Optinal : null일 경우 Optional로 감싸서 처리
    Optional<BoardEntity> findById(Long id);
    List<BoardEntity> findAll();
}
