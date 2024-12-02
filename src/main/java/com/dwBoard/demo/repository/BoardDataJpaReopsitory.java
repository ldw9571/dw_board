package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDataJpaReopsitory extends JpaRepository<BoardEntity, Long>,BoardRepository {

}
