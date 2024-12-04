package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardRepositoryCustom {

    // 기본적인 CRUD와 페이징을 위한 메서드
    Page<BoardEntity> findAll(Pageable pageable);

}
