package com.dwBoard.demo.repository;

import com.dwBoard.demo.Entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDataJpaReopsitory extends JpaRepository<BoardEntity, Long>,BoardRepository {

}
