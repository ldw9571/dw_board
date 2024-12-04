package com.dwBoard.demo.repository;

import com.dwBoard.demo.entity.BoardEntity;
import com.dwBoard.demo.entity.QBoardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        // 검색 조건에 맞는 필터링
        if ("title".equals(searchType)) {
            builder.and(qBoardEntity.title.containsIgnoreCase(searchText));
        } else if ("content".equals(searchType)) {
            builder.and(qBoardEntity.content.containsIgnoreCase(searchText));
        } else if ("writer".equals(searchType)) {
            builder.and(qBoardEntity.writer.containsIgnoreCase(searchText));
        }

        // 쿼리 실행
        List<BoardEntity> results = queryFactory.selectFrom(qBoardEntity)
                .where(builder)
                .offset(pageable.getOffset())  // 페이징 처리
                .limit(pageable.getPageSize())  // 페이지 크기 설정
                .orderBy(getOrderSpecifier(pageable)) // 정렬 처리
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }

    // Sort에 맞게 정렬을 처리하는 메서드
    private OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;

        // Sort의 첫 번째 요소를 기준으로 정렬
        if (pageable.getSort().isSorted()) {
            Sort.Order order = pageable.getSort().iterator().next();
            if ("dateTime".equals(order.getProperty())) {
                return order.getDirection().isAscending() ? qBoardEntity.dateTime.asc() : qBoardEntity.dateTime.desc();
            } else if ("title".equals(order.getProperty())) {
                return order.getDirection().isAscending() ? qBoardEntity.title.asc() : qBoardEntity.title.desc();
            } else if ("writer".equals(order.getProperty())) {
                return order.getDirection().isAscending() ? qBoardEntity.writer.asc() : qBoardEntity.writer.desc();
            }
        }

        return qBoardEntity.dateTime.desc();  // 기본 정렬 (예: 날짜 내림차순)
    }
}
