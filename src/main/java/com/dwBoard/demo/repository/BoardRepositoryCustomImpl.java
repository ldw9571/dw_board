package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;
import com.dwBoard.demo.entity.QBoardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Page<BoardEntity> findBySearchTextAndType(String searchText, String searchType, Pageable pageable) {
        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;

        BooleanBuilder builder = new BooleanBuilder();

        if ("title".equals(searchType)) {
            builder.and(qBoardEntity.title.containsIgnoreCase(searchText));
        } else if ("content".equals(searchType)) {
            builder.and(qBoardEntity.content.containsIgnoreCase(searchText));
        } else if ("writer".equals(searchType)) {
            builder.and(qBoardEntity.writer.containsIgnoreCase(searchText));
        }

        List<BoardEntity> results = queryFactory.selectFrom(qBoardEntity)
                .where(builder)
                .offset(pageable.getOffset())  // 페이징 처리
                .limit(pageable.getPageSize())  // 페이지 크기 설정
                .orderBy(qBoardEntity.dateTime.desc())  // 정렬 (예: 날짜 내림차순)
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }
}
