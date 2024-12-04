package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    BoardEntity save(BoardEntity boardRequestDTO);
    //Optinal : null일 경우 Optional로 감싸서 처리
    Optional<BoardEntity> findById(Long id);
    List<BoardEntity> findAll();

    @Query("SELECT b FROM BoardEntity b WHERE "
            + "( :searchType = 'title' AND b.title LIKE %:searchText%) "
            + "OR ( :searchType = 'writer' AND b.writer LIKE %:searchText%) "
            + "OR ( :searchType = 'content' AND b.content LIKE %:searchText%)")
    Page<BoardEntity> findBySearchTextAndType(@Param("searchText") String searchText,
                                              @Param("searchType") String searchType,
                                              Pageable pageable);
}
