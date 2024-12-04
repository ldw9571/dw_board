package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardEntity> findBySearchTextAndType(String searchText, String searchType, Pageable pageable);
}
